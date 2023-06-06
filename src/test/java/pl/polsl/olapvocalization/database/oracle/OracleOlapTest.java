package pl.polsl.olapvocalization.database.oracle;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import oracle.olapi.data.cursor.CompoundCursor;
import oracle.olapi.data.cursor.CursorManager;
import oracle.olapi.data.cursor.ValueCursor;
import oracle.olapi.data.source.DataProvider;
import oracle.olapi.data.source.Source;
import oracle.olapi.metadata.mdm.*;
import oracle.olapi.session.UserSession;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class OracleOlapTest {

    // jdbc:oracle:thin:@serverName:portNumber:sid
    private final String URL = "jdbc:oracle:thin:@localhost:1521/olapPDB";
    private final String USER = "Global";
    private final String PASSWORD = "root";

    private final String DATABASE_SCHEMA_NAME = "GLOBAL";

    @Test
    public void connectionTest() throws SQLException {
        final OracleConnection conn = getConnection(URL, USER, PASSWORD);
        assertNotNull(conn);
        conn.close();
    }

    @Test
    public void olapMetadataTest() throws SQLException {

        final OracleConnection conn = getConnection(URL, USER, PASSWORD);
        final DataProvider dp = getDataProvider(conn);
        final MdmMetadataProvider mdmMetadataProvider = getMdmMetadataProvider(dp);

        final MdmSchema rootSchema = mdmMetadataProvider.getRootSchema();

        final List<MdmCube> cubes = rootSchema.getCubes();
        log.info("---CUBE NAMES---");
        cubes.forEach(cube -> log.info(cube.getName()));

        final List<MdmDimension> dimensions = rootSchema.getDimensions();
        log.info("---DIMENSIONS---");
        dimensions.forEach(dimension -> log.info(dimension.getName()));

        final List<MdmObject> measures = rootSchema.getMeasures();
        log.info("---MEASURES---");
        measures.forEach(measure -> log.info(measure.getName()));

        dp.close();
        conn.close();
    }

    @Test
    public void olapCubeMetadataTest() throws SQLException {

        final OracleConnection conn = getConnection(URL, USER, PASSWORD);
        final DataProvider dp = getDataProvider(conn);
        final MdmMetadataProvider mdmMetadataProvider = getMdmMetadataProvider(dp);

        final MdmRootSchema rootSchema = (MdmRootSchema) mdmMetadataProvider.getRootSchema();

        final MdmDatabaseSchema databaseSchema = rootSchema.getDatabaseSchema(DATABASE_SCHEMA_NAME);
        final List<MdmCube> cubes = databaseSchema.getCubes();

        cubes.forEach(cube -> {
            log.info(" - Cube: " + cube.getName());
            log.info(" --- Dimensions: ");
            cube.getDimensions().forEach(dimension -> {
                log.info(" ------ " + ((MdmDimension) dimension).getName());
                log.info(" --------- Hierarchies: ");
                final List hierarchies = ((MdmDimension) dimension).getPrimaryDimension().getHierarchies();
                hierarchies.forEach(hierarchy -> {
                    log.info(" ------------ " + ((MdmLevelHierarchy) hierarchy).getName());
                    final List<MdmHierarchyLevel> hierarchyLevels = ((MdmLevelHierarchy) hierarchy).getHierarchyLevels();
                    log.info(" --------------- Levels: ");
                    hierarchyLevels.forEach(level -> log.info(" ------------------ " + level.getName()));
                });
                final List attributes = ((MdmDimension) dimension).getAttributes();
                log.info(" --------- Attributes: ");
                attributes.forEach(attribute -> log.info(" ------------ " + ((MdmAttribute) attribute).getName()));

            });
            log.info(" --- Measures: ");
            cube.getMeasures().forEach(measure -> log.info(" ------ " + measure.getName()));
        });

        dp.close();
        conn.close();
    }

    @Test
    public void olapCubeQueryTest() {
        final OracleConnection conn = getConnection(URL, USER, PASSWORD);
        final DataProvider dp = getDataProvider(conn);
        final MdmMetadataProvider mdmMetadataProvider = getMdmMetadataProvider(dp);
        final MdmRootSchema rootSchema = (MdmRootSchema) mdmMetadataProvider.getRootSchema();
        final MdmDatabaseSchema mdmDBSchema = rootSchema.getDatabaseSchema(DATABASE_SCHEMA_NAME);

        // Get the primary dimensions and the measure.
        final List<MdmPrimaryDimension> dimensions = Arrays.asList(
                (MdmPrimaryDimension) mdmDBSchema.getDimensions().stream().filter(dimension -> ((MdmPrimaryDimension) dimension).getName().equals("PRODUCT")).findAny().get(),
                (MdmPrimaryDimension) mdmDBSchema.getDimensions().stream().filter(dimension -> ((MdmPrimaryDimension) dimension).getName().equals("CUSTOMER")).findAny().get(),
                (MdmPrimaryDimension) mdmDBSchema.getDimensions().stream().filter(dimension -> ((MdmPrimaryDimension) dimension).getName().equals("CHANNEL")).findAny().get(),
                (MdmPrimaryDimension) mdmDBSchema.getDimensions().stream().filter(dimension -> ((MdmPrimaryDimension) dimension).getName().equals("TIME")).findAny().get()
        );

        final MdmMeasure units = (MdmMeasure) mdmDBSchema.getMeasures().stream().filter(measure -> ((MdmMeasure) measure).getName().equals("UNIT_PRICE")).findAny().get();

        Source unitPriceByMonth = units.getSource()
                .join(dimensions.get(0).getSource())
                .join(dimensions.get(3).getSource());
        // Commit the current Transaction (code not shown).

        // Create a Cursor for unitPriceByMonth.
        CursorManager cursorMngr = dp.createCursorManager(unitPriceByMonth);
        CompoundCursor rootCursor = (CompoundCursor) cursorMngr.createCursor();

        // Determine a starting position and the number of rows to display.
        int start = 7;
        int numRows = 12;

        log.info("Month     Product     Unit Price");
        log.info("-------   --------   ----------");

        // Iterate through the specified positions of the root CompoundCursor.
        // Assume that the Cursor contains at least (start + numRows) positions.
        for (int pos = start; pos < start + numRows; pos++) {
            // Set the position of the root CompoundCursor.
            rootCursor.setPosition(pos);
            // Print the local values of the output and base ValueCursors.
            // The getLocalValue method gets the local value from the unique
            // value of a dimension element.
            String timeValue = ((ValueCursor) rootCursor.getOutputs().get(0))
                    .getCurrentString();
            String timeLocVal = timeValue;
            String prodValue = ((ValueCursor) rootCursor.getOutputs().get(1))
                    .getCurrentString();
            String prodLocVal = prodValue;
            Object price = rootCursor.getValueCursor().getCurrentValue();
            log.info(timeLocVal + "   " + prodLocVal + "   " + price);
        }

        cursorMngr.close();
    }

    private OracleConnection getConnection(final String url, final String user, final String password) {
        OracleConnection conn = null;
        try {
            final OracleDataSource ods = new OracleDataSource();
            ods.setURL(url);
            ods.setUser(user);
            ods.setPassword(password);
            conn = (oracle.jdbc.OracleConnection) ods.getConnection();
        } catch (SQLException e) {
            log.error("Connection attempt failed. " + e);
        }
        return conn;
    }

    private DataProvider getDataProvider(final OracleConnection conn) {
        final DataProvider dp = new DataProvider();
        try {
            final UserSession session = dp.createSession(conn);
        } catch (SQLException e) {
            log.error("Could not create a UserSession. " + e);
        }
        return dp;
    }

    private MdmMetadataProvider getMdmMetadataProvider(final DataProvider dp) {
        MdmMetadataProvider mp = null;
        try {
            mp = dp.getMdmMetadataProvider();
        } catch (Exception e) {
            log.error("Cannot get the MDM metadata provider. " + e);
        }
        return mp;
    }
}
