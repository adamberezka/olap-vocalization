package pl.polsl.olapvocalization.infrastructure.insight.modules.topk;

import lombok.RequiredArgsConstructor;
import pl.polsl.olapvocalization.infrastructure.insight.Insight;
import pl.polsl.olapvocalization.infrastructure.insight.InsightGeneratorModule;
import pl.polsl.olapvocalization.infrastructure.insight.cost.InsightCoverageCalculator;
import pl.polsl.olapvocalization.infrastructure.insight.cost.VocalizationCostCalculator;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryResult;

@RequiredArgsConstructor
public class TopKInsightGeneratorModule implements InsightGeneratorModule {

    public final InsightCoverageCalculator coverageCalculator;
    public final VocalizationCostCalculator vocalizationCostCalculator;

    @Override
    public Insight generateInsight(final QueryResult previousQueryResult, final QueryResult currentQueryResult) {
        return null;
    }

}
