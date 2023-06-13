package pl.polsl.olapvocalization.infrastructure.insight;

import pl.polsl.olapvocalization.infrastructure.database.query.QueryResult;

public interface InsightGeneratorModule {

    Insight generateInsight(QueryResult previousQueryResult, QueryResult currentQueryResult);
}
