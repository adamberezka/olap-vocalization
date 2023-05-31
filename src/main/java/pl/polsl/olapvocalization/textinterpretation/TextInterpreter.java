package pl.polsl.olapvocalization.textinterpretation;

import java.util.Arrays;

public class TextInterpreter {

    public static String[] tokenizeText(String text){
        return text.split(" ");
    }

    private static double simTokens(String x, String y){
        return 1 - Levenshtein.normalizedDistance(x, y);
    }

    public static float simPhrases(String[] t, String[] w){



        return 0;
    }

}
