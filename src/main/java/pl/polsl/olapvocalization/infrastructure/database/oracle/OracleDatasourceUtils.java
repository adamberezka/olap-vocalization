package pl.polsl.olapvocalization.infrastructure.database.oracle;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import oracle.olapi.data.source.DataProvider;
import oracle.olapi.metadata.mdm.MdmMetadataProvider;
import oracle.olapi.session.UserSession;

import java.sql.SQLException;

@Slf4j
public class OracleDatasourceUtils {

    public static String getConnectionString(final String hostAddress, final String port, final String databaseName) {
        log.info("Getting connection string");
        return String.format("jdbc:oracle:thin:@%s:%s/%s", hostAddress, port, databaseName);
    }

    public static OracleConnection getConnection(final String url, final String user, final String password) throws SQLException {
        log.info("Getting oracle connection");
        OracleConnection conn = null;
        try {
            final OracleDataSource ods = new OracleDataSource();
            ods.setURL(url);
            ods.setUser(user);
            ods.setPassword(password);
            conn = (oracle.jdbc.OracleConnection) ods.getConnection();
        } catch (final SQLException e) {
            log.error("Connection attempt failed. " + e);
            throw e;
        }
        return conn;
    }

    public static DataProvider getDataProvider(final OracleConnection conn) throws SQLException {
        log.info("Getting oracle data provider");
        final DataProvider dp = new DataProvider();
        try {
            log.info("Creating oracle user session");
            final UserSession session = dp.createSession(conn);
        } catch (final SQLException e) {
            log.error("Could not create a UserSession. " + e);
            throw e;
        }
        return dp;
    }

    public static MdmMetadataProvider getMdmMetadataProvider(final DataProvider dp) {
        log.info("Getting oracle metadata provider");
        final MdmMetadataProvider mp;
        try {
            mp = dp.getMdmMetadataProvider();
        } catch (final Exception e) {
            log.error("Cannot get the MDM metadata provider. " + e);
            throw e;
        }
        return mp;
    }
}
