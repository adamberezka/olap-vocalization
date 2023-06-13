package pl.polsl.olapvocalization.infrastructure.validator;

import pl.polsl.olapvocalization.infrastructure.database.query.InitialQuery;
import pl.polsl.olapvocalization.infrastructure.database.query.Query;

public class SimpleQueryValidator implements QueryValidator {

    // TODO

    @Override
    public QueryValidationResult validateQuery(final Query query) {
        return new QueryValidationResult(true);
    }

    @Override
    public QueryValidationResult validateQuery(final InitialQuery initialQuery, final Query queryRefinement) {
        return new QueryValidationResult(true);
    }
}
