package pl.polsl.olapvocalization.infrastructure.database.logging;

import lombok.extern.slf4j.Slf4j;
import pl.polsl.olapvocalization.infrastructure.database.QueryExecutor;
import pl.polsl.olapvocalization.infrastructure.database.query.Query;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryResult;

import java.util.ArrayList;

@Slf4j
public class OnlyLoggingQueryExecutor implements QueryExecutor {

    @Override
    public QueryResult executeQuery(final Query query) {
        log.debug(query.toString());
        return new QueryResult(new ArrayList<>());
    }
}
