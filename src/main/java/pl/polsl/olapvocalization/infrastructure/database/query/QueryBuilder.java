package pl.polsl.olapvocalization.infrastructure.database.query;

import lombok.RequiredArgsConstructor;
import pl.polsl.olapvocalization.infrastructure.textinterpreter.TextInterpreter;

@RequiredArgsConstructor
public class QueryBuilder {

    private final TextInterpreter textInterpreter;

    public Query getQuery(final String queryInput) {
        return null;
    }
}
