package pl.polsl.olapvocalization.infrastructure.textinterpreter;

import java.util.List;

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
    public List<String> getInterpretedText() {
        return null;
    }
}
