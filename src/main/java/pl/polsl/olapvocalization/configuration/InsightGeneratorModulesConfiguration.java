package pl.polsl.olapvocalization.configuration;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.polsl.olapvocalization.infrastructure.insight.InsightGeneratorModule;
import pl.polsl.olapvocalization.infrastructure.insight.cost.InsightCoverageCalculator;
import pl.polsl.olapvocalization.infrastructure.insight.cost.VocalizationCostCalculator;
import pl.polsl.olapvocalization.infrastructure.insight.cost.WordsCountVocalizationCostCalculator;
import pl.polsl.olapvocalization.infrastructure.insight.modules.test.TestInsightGeneratorModule;
import pl.polsl.olapvocalization.infrastructure.insight.modules.topk.TopKInsightGeneratorModule;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class InsightGeneratorModulesConfiguration {

    OlapVocalizationApplicationConfigurationProperties properties;

    @Bean
    public List<InsightGeneratorModule> insightGeneratorModules(final InsightCoverageCalculator insightCoverageCalculator,
                                                                final VocalizationCostCalculator vocalizationCostCalculator) {
        final ArrayList<InsightGeneratorModule> insightGeneratorModules = new ArrayList<>();

        if (properties.getInsightGeneratorModules().contains(OlapVocalizationApplicationConfigurationProperties.InsightGeneratorModuleType.TEST)) {
            insightGeneratorModules.add(new TestInsightGeneratorModule(insightCoverageCalculator, vocalizationCostCalculator));
        }

        if (properties.getInsightGeneratorModules().contains(OlapVocalizationApplicationConfigurationProperties.InsightGeneratorModuleType.TOPK)) {
            insightGeneratorModules.add(new TopKInsightGeneratorModule(insightCoverageCalculator, vocalizationCostCalculator));
        }

        return insightGeneratorModules;
    }

    @Bean
    @ConditionalOnProperty(name = "olap.vocalization.insight-vocalization-cost-calculator", havingValue = "WORDS_COUNT")
    public VocalizationCostCalculator wordsCountVocalizationCostCalculator() {
        return new WordsCountVocalizationCostCalculator();
    }

}
