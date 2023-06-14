package pl.polsl.olapvocalization.infrastructure.database.query;

import lombok.RequiredArgsConstructor;
import pl.polsl.olapvocalization.infrastructure.textinterpreter.TextInterpreter;
import pl.polsl.olapvocalization.olap.query.queryclauses.GroupByClause;
import pl.polsl.olapvocalization.olap.query.queryclauses.MeasureClause;
import pl.polsl.olapvocalization.olap.query.queryclauses.SCN;
import pl.polsl.olapvocalization.olap.query.queryclauses.SelectionClause;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@RequiredArgsConstructor
public class QueryBuilder {

    private final TextInterpreter textInterpreter;

    private Stack reverseStack(Stack inputStack){
        Queue queue = new LinkedList<>();
        while (!inputStack.isEmpty()) {
            queue.add(inputStack.pop());
        }
        while (!queue.isEmpty()) {
            inputStack.add(queue.remove());
        }
        return inputStack;
    }

    public Query getQuery(final String queryInput) {

        Stack<String> targetSequence = reverseStack(textInterpreter.getInterpretedText(queryInput));

        if (textInterpreter.isRefinementDeterminer(targetSequence.peek())) {
            QueryRefinement queryRefinement = new QueryRefinement();

            queryRefinement.setType(textInterpreter.getRefinementType(targetSequence.peek()));

            // TODO

            return queryRefinement;
        } else {
            InitialQuery initialQuery = new InitialQuery();

            // build measure clause
            MeasureClause mc = new MeasureClause();

            while (!targetSequence.empty() && targetSequence.peek() != textInterpreter.getMeasureClauseTerminatorToken()) {
                String value1 = targetSequence.pop();
                String value2 = targetSequence.pop();
                mc.addMeasureClausePair(value1, value2);
            }
            initialQuery.setMeasureClause(mc);
            targetSequence.pop();

            // build selection clause
            SelectionClause sc = new SelectionClause();

            while (!targetSequence.empty() && targetSequence.peek() != textInterpreter.getSelectionClauseTerminatorToken()) {
                SCN scn = new SCN();

                if (targetSequence.peek() == textInterpreter.getNegationToken()) {
                    scn.setIsNegated(true);
                    targetSequence.pop();
                }

                String value1 = targetSequence.pop();
                String value2 = targetSequence.pop();
                String value3 = targetSequence.pop();

                scn.setSelectionValues(Arrays.asList(value1, value2, value3));

                sc.addSCN(scn);
            }
            initialQuery.setSelectionClause(sc);
            targetSequence.pop();

            // build group by clause
            GroupByClause gc = new GroupByClause();

            while (!targetSequence.empty()) {
                String attribute = targetSequence.pop();
                gc.addGroupByAttribute(attribute);
            }

            initialQuery.setGroupByClause(gc);

            return initialQuery;
        }

    }
}
