package pl.polsl.olapvocalization.configuration.datasource.oracle;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleConnection;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryExecutor;
import pl.polsl.olapvocalization.infrastructure.database.metadata.MetadataRepository;
import pl.polsl.olapvocalization.infrastructure.database.oracle.OracleDatasourceUtils;
import pl.polsl.olapvocalization.infrastructure.database.oracle.OracleMetadataRepository;
import pl.polsl.olapvocalization.infrastructure.database.oracle.OracleQueryExecutor;

import java.sql.SQLException;

@Configuration
@ConditionalOnProperty(name = "olap.vocalization.query-executor", havingValue = "ORACLE")
@RequiredArgsConstructor
public class OracleDatasourceConfiguration {

    private final OracleDatasourceConfigurationProperties properties;

    @Bean
    public QueryExecutor oracleQueryExecutor() {
        return new OracleQueryExecutor();
    }

    @Bean
    public MetadataRepository oracleMetadataRepository(final OracleConnection oracleConnection) throws SQLException {
        return OracleMetadataRepository.create(oracleConnection, properties.getDatabaseSchemaName());
    }

    @Bean
    public OracleConnection oracleConnection() throws SQLException {
        return OracleDatasourceUtils.getConnection(
                OracleDatasourceUtils.getConnectionString(
                        properties.getHostAddress(),
                        properties.getPort().toString(),
                        properties.getDatabaseName()
                ),
                properties.getUsername(),
                properties.getPassword()
        );
    }

}
