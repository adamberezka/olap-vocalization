package pl.polsl.olapvocalization.configuration.datasource.oracle;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.polsl.olapvocalization.infrastructure.database.QueryExecutor;
import pl.polsl.olapvocalization.infrastructure.database.metadata.MetadataRepository;
import pl.polsl.olapvocalization.infrastructure.database.oracle.OracleMetadataRepository;
import pl.polsl.olapvocalization.infrastructure.database.oracle.OracleQueryExecutor;

@Configuration
@ConditionalOnProperty(name = "olap.vocalization.query-executor", havingValue = "ORACLE")
@AllArgsConstructor
public class OracleDatasourceConfiguration {

    OracleDatasourceConfigurationProperties properties;

    @Bean
    public QueryExecutor oracleQueryExecutor() {
        return new OracleQueryExecutor();
    }

    @Bean
    public MetadataRepository oracleMetadataRepository() {
        return new OracleMetadataRepository();
    }

}
