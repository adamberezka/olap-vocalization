package pl.polsl.olapvocalization.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "olap.vocalization")
@Data
@Validated
public class OlapVocalizationApplicationConfigurationProperties {

    @NotNull
    private InputManagerType inputManager;
    @NotNull
    private TextInterpreterType textInterpreter;
    @NotNull
    private QueryValidatorType queryValidator;
    @NotNull
    private QueryExecutorType queryExecutor;
    @NotNull
    private InsightVocalizatorType insightVocalizator;
    @NotNull
    private InsightVocalizationCostCalculatorType insightVocalizationCostCalculator;
    @NotEmpty
    private Set<InsightGeneratorModuleType> insightGeneratorModules = new HashSet<>();

    enum InputManagerType {
        CONSOLE
    }

    enum TextInterpreterType {
        SIMPLE,
        COOL
    }

    enum QueryValidatorType {
        SIMPLE
    }

    enum QueryExecutorType {
        LOGGING,
        ORACLE
    }

    enum InsightVocalizatorType {
        LOGGING
    }

    enum InsightVocalizationCostCalculatorType {
        WORDS_COUNT
    }

    enum InsightGeneratorModuleType {
        TEST,
        TOPK
    }

}
