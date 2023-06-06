package pl.polsl.olapvocalization.infrastructure.validator;

import pl.polsl.olapvocalization.olap.query.Query;

public interface QueryValidator {

    QueryValidationResult validateQuery(Query query);

}
