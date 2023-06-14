package pl.polsl.olapvocalization.configuration.datasource.oracle;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Configuration
@ConditionalOnProperty(name = "olap.vocalization.query-executor", havingValue = "ORACLE")
@ConfigurationProperties(prefix = "olap.vocalization.datasource.oracle")
@Validated
@Data
public class OracleDatasourceConfigurationProperties {

    @NotEmpty
    private String databaseSchemaName;
    @NotEmpty
    private String username;
    private String password = "";
    @NotEmpty
    private String hostAddress;
    private Integer port = 1521;
    @NotEmpty
    private String databaseName;

}
