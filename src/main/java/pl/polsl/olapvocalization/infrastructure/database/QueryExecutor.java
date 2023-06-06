package pl.polsl.olapvocalization.infrastructure.database;

import pl.polsl.olapvocalization.olap.query.Query;
import pl.polsl.olapvocalization.olap.query.QueryResult;

import java.util.List;

public interface QueryExecutor {

    QueryResult executeQuery(Query query);

}
