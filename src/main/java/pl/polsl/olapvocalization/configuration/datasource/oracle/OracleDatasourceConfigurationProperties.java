package pl.polsl.olapvocalization.configuration.datasource.oracle;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConditionalOnProperty(name = "olap.vocalization.query-executor", havingValue = "ORACLE")
@ConfigurationProperties(prefix = "olap.vocalization.datasource.oracle")
@Validated
@Data
public class OracleDatasourceConfigurationProperties {



}
