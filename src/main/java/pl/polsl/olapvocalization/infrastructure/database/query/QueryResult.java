package pl.polsl.olapvocalization.infrastructure.database.query;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
public class QueryResult {

    List<OLAPRecord> records;
    List<ColumnDefinition> columns;

    @Data
    class ColumnDefinition<T> {
        String name;
        Class<T> type;
    }

    class ColumnType {

    }
}
