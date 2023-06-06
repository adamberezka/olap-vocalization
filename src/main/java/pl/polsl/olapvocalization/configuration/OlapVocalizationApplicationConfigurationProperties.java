package pl.polsl.olapvocalization.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "olap.vocalization")
@Data
public class OlapVocalizationApplicationConfigurationProperties {

    InputManagerType inputManager;
    TextInterpreterType textInterpreter;
    QueryValidatorType queryValidator;
    InsightVocalizatorType insightVocalizator;
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

    enum InsightVocalizatorType {
        LOGGING
    }

    enum InsightGeneratorModuleType {
        TEST,
        TOPK
    }

}
