package pl.polsl.olapvocalization.infrastructure.insight.modules.test;

import lombok.RequiredArgsConstructor;
import pl.polsl.olapvocalization.infrastructure.insight.Insight;
import pl.polsl.olapvocalization.infrastructure.insight.InsightGeneratorModule;
import pl.polsl.olapvocalization.infrastructure.insight.cost.InsightCoverageCalculator;
import pl.polsl.olapvocalization.infrastructure.insight.cost.VocalizationCostCalculator;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryResult;

@RequiredArgsConstructor
public class TestInsightGeneratorModule implements InsightGeneratorModule {

    public final InsightCoverageCalculator coverageCalculator;
    public final VocalizationCostCalculator vocalizationCostCalculator;

    @Override
    public Insight generateInsight(final QueryResult previousQueryResult, final QueryResult currentQueryResult) {
        return new Insight("TEST INSIGHT", 1d, 1d, 0d);
    }

}
