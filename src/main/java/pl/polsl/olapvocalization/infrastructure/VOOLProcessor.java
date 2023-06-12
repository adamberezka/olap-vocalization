package pl.polsl.olapvocalization.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public void run() {

        while (true) { // TODO add exit

            final String input = inputManager.getInput();
            final Query query = queryBuilder.getQuery(input);

            final QueryValidationResult queryValidationResult = queryValidator.validateQuery(userSession.getCurrentQuery(), query);

            if (queryValidationResult.getSuccess()) {
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


}
