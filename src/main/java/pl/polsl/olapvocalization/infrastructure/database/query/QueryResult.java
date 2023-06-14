package pl.polsl.olapvocalization.infrastructure.database.query;

import lombok.Value;

import java.util.List;

@Value
public class QueryResult {
    List<OLAPRecord> records;
}
