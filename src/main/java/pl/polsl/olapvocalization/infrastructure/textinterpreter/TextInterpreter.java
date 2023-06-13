package pl.polsl.olapvocalization.infrastructure.textinterpreter;

import pl.polsl.olapvocalization.infrastructure.database.query.QueryRefinement;

import java.util.Stack;

public interface TextInterpreter {

    Stack<String> getInterpretedText(String input);

    String getMeasureClauseTerminatorToken();
    String getSelectionClauseTerminatorToken();
    String getNegationToken();
    Boolean isRefinementDeterminer(String input);

    QueryRefinement.RefinementType getRefinementType(String input);

}
