package pl.polsl.olapvocalization.infrastructure.insight;

import pl.polsl.olapvocalization.olap.query.QueryResult;

public interface InsightGeneratorModule {

    Insight generateInsight(QueryResult previousQueryResult, QueryResult currentQueryResult);
}
