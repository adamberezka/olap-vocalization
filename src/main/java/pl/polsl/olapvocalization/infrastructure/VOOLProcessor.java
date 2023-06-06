package pl.polsl.olapvocalization.infrastructure;

import lombok.RequiredArgsConstructor;
import pl.polsl.olapvocalization.infrastructure.input.InputManager;
import pl.polsl.olapvocalization.infrastructure.validator.QueryValidator;
import pl.polsl.olapvocalization.olap.query.QueryBuilder;

@RequiredArgsConstructor
public class VOOLProcessor {

    private final UserSession userSession;
    private final InputManager inputManager;
    private final QueryBuilder queryBuilder;
    private final QueryValidator queryValidator;

}
