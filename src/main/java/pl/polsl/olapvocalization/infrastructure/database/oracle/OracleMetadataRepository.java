package pl.polsl.olapvocalization.infrastructure.database.oracle;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.olapi.data.source.DataProvider;
import oracle.olapi.metadata.mdm.*;
import pl.polsl.olapvocalization.infrastructure.database.metadata.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Slf4j
public class OracleMetadataRepository implements MetadataRepository {

    DatasourceMetadata metadata;

    public static OracleMetadataRepository create(final OracleConnection oracleConnection,
                                                  final String databaseSchemaName) throws SQLException {

        final DataProvider dp = OracleDatasourceUtils.getDataProvider(oracleConnection);
        final MdmMetadataProvider mdmMetadataProvider = OracleDatasourceUtils.getMdmMetadataProvider(dp);
        final MdmRootSchema rootSchema = (MdmRootSchema) mdmMetadataProvider.getRootSchema();
        final MdmDatabaseSchema databaseSchema = rootSchema.getDatabaseSchema(databaseSchemaName);
        final List<MdmCube> cubes = databaseSchema.getCubes();

        final List<CubeMetadata> cubesMetadata = cubes.stream().map(OracleMetadataRepository::toDomain)
                .collect(Collectors.toList());

        dp.close();

        return new OracleMetadataRepository(new DatasourceMetadata(cubesMetadata));
    }

    private static CubeMetadata toDomain(final MdmCube mdmCube) {
        return new CubeMetadata(
                mdmCube.getName(),
                ((List<MdmDimension>) mdmCube.getDimensions()).stream().map(OracleMetadataRepository::toDomain).collect(Collectors.toList()),
                mdmCube.getMeasures().stream().map(OracleMetadataRepository::toDomain).collect(Collectors.toList())
        );
    }

    private static CubeDimensionMetadata toDomain(final MdmDimension mdmDimension) {
        return new CubeDimensionMetadata(
                mdmDimension.getName(),
                ((List<MdmLevelHierarchy>) mdmDimension.getPrimaryDimension().getHierarchies()).stream().map(OracleMetadataRepository::toDomain).collect(Collectors.toList()),
                ((List<MdmAttribute>) mdmDimension.getAttributes()).stream().map(OracleMetadataRepository::toDomain).collect(Collectors.toList())
        );
    }

    private static CubeMeasureMetadata toDomain(final MdmMeasure mdmMeasure) {
        return new CubeMeasureMetadata(
                mdmMeasure.getName()
        );
    }

    private static CubeDimensionHierarchyMetadata toDomain(final MdmLevelHierarchy hierarchy) {
        return new CubeDimensionHierarchyMetadata(
                hierarchy.getName(),
                hierarchy.getHierarchyLevels().stream().map(OracleMetadataRepository::toDomain).collect(Collectors.toList()),
                ((List<MdmAttribute>) hierarchy.getAttributes()).stream().map(OracleMetadataRepository::toDomain).collect(Collectors.toList())
        );
    }

    private static CubeDimensionHierarchyLevelMetadata toDomain(final MdmHierarchyLevel hierarchyLevel) {
        return new CubeDimensionHierarchyLevelMetadata(
                hierarchyLevel.getName(),
                hierarchyLevel.getLevelDepth(),
                ((List<MdmAttribute>) hierarchyLevel.getAttributes()).stream().map(OracleMetadataRepository::toDomain).collect(Collectors.toList())
        );
    }

    private static AttributeMetadata toDomain(final MdmAttribute mdmAttribute) {
        return new AttributeMetadata(
                mdmAttribute.getName()
        );
    }


}
