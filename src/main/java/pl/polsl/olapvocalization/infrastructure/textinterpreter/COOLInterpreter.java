package pl.polsl.olapvocalization.infrastructure.textinterpreter;

import pl.polsl.olapvocalization.infrastructure.database.query.QueryRefinement;

import java.util.List;
import java.util.Stack;

public class COOLInterpreter implements TextInterpreter {

    public static String[] tokenizeText(String text){
        return text.split(" ");
    }

    private static double simTokens(String x, String y){
        return 1 - Levenshtein.normalizedDistance(x, y);
    }

    public static float simPhrases(String[] t, String[] w){

        // TODO

        return 0;
    }

    @Override
    public Stack<String> getInterpretedText(String input) {
        return null;
    }

    @Override
    public String getMeasureClauseTerminatorToken() {
        return null;
    }

    @Override
    public String getSelectionClauseTerminatorToken() {
        return null;
    }

    @Override
    public String getNegationToken() {
        return null;
    }

    @Override
    public Boolean isRefinementDeterminer(String input) {
        return null;
    }

    @Override
    public QueryRefinement.RefinementType getRefinementType(String input) {
        return null;
    }
}
