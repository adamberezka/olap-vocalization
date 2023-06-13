package pl.polsl.olapvocalization.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "olap.vocalization")
@Data
@Validated
public class OlapVocalizationApplicationConfigurationProperties {

    @NotNull
    InputManagerType inputManager;
    @NotNull
    TextInterpreterType textInterpreter;
    @NotNull
    QueryValidatorType queryValidator;
    @NotNull
    QueryExecutorType queryExecutor;
    @NotNull
    InsightVocalizatorType insightVocalizator;
    @NotNull
    InsightVocalizationCostCalculatorType insightVocalizationCostCalculator;
    @NotEmpty
    List<InsightGeneratorModuleType> insightGeneratorModules = new ArrayList<>();

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
