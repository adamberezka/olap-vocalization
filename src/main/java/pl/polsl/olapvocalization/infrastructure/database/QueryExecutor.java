package pl.polsl.olapvocalization.infrastructure.database;

import pl.polsl.olapvocalization.olap.query.Query;

import java.util.List;

public interface QueryExecutor {

    List<OLAPRecord> executeQuery(Query query);

}
