package pl.polsl.olapvocalization.infrastructure.database;

import pl.polsl.olapvocalization.infrastructure.database.query.Query;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryResult;

public interface QueryExecutor {

    QueryResult executeQuery(Query query);

}
