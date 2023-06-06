package pl.polsl.olapvocalization.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.polsl.olapvocalization.infrastructure.database.OLAPRecord;
import pl.polsl.olapvocalization.infrastructure.database.QueryExecutor;
import pl.polsl.olapvocalization.infrastructure.input.InputManager;
import pl.polsl.olapvocalization.infrastructure.insight.Insight;
import pl.polsl.olapvocalization.infrastructure.insight.InsightGenerator;
import pl.polsl.olapvocalization.infrastructure.validator.QueryValidationResult;
import pl.polsl.olapvocalization.infrastructure.validator.QueryValidator;
import pl.polsl.olapvocalization.infrastructure.vocalization.InsightVocalizator;
import pl.polsl.olapvocalization.olap.query.Query;
import pl.polsl.olapvocalization.olap.query.QueryBuilder;
import pl.polsl.olapvocalization.olap.query.QueryRefinement;
import pl.polsl.olapvocalization.olap.query.QueryResult;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class VOOLProcessor {

    private final UserSession userSession;
    private final InputManager inputManager;
    private final QueryBuilder queryBuilder;
    private final QueryValidator queryValidator;
    private final QueryExecutor queryExecutor;
    private final InsightGenerator insightGenerator;
    private final InsightVocalizator insightVocalizator;

    private QueryState queryState = QueryState.INITIAL;

    public void run() {
        while (true) {

            final String input = inputManager.getInput();

            final QueryValidationResult queryValidationResult;

            if (queryState == QueryState.INITIAL) {
                final Query initialQuery = queryBuilder.getInitialQuery(input);
                queryValidationResult = queryValidator.validateQuery(initialQuery);
                if (queryValidationResult.getSuccess())
                    userSession.setInitialQuery(initialQuery);
                queryState = QueryState.REFINEMENT;
            } else {
                final QueryRefinement queryRefinement = queryBuilder.getQueryRefinement(input);
                final Query updatedQuery = userSession.getCurrentQuery().updateQuery(queryRefinement);
                queryValidationResult = queryValidator.validateQuery(updatedQuery);
                if (queryValidationResult.getSuccess())
                    userSession.addRefinement(queryRefinement);
            }

            if(queryValidationResult.getSuccess()) {
                final QueryResult queryResult = queryExecutor.executeQuery(userSession.getCurrentQuery());
                userSession.updateResult(queryResult);
                final List<Insight> insights = insightGenerator.generateInsights(userSession.getPreviousResult(), userSession.getCurrentResult());
                final List<String> insightDescriptions = insights.stream().map(Insight::getDescription).collect(Collectors.toList());
                insightDescriptions.forEach(insightVocalizator::vocalize);
            } else {
                log.error("Query validation error");
            }
        }
    }


    enum QueryState {
        INITIAL,
        REFINEMENT
    }

}
