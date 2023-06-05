package pl.polsl.olapvocalization.textinterpretation;

import java.util.Arrays;

public class Levenshtein {

    static int distance(String x, String y) {
        if (x.isEmpty()) {
            return y.length();
        }

        if (y.isEmpty()) {
            return x.length();
        }

        int substitution = distance(x.substring(1), y.substring(1))
                + costOfSubstitution(x.charAt(0), y.charAt(0));
        int insertion = distance(x, y.substring(1)) + 1;
        int deletion = distance(x.substring(1), y) + 1;

        return min(substitution, insertion, deletion);
    }

    static double normalizedDistance(String x, String y){
        final double distance = (double)distance(x, y);

        if (x.length() >= y.length()) {
            return distance / x.length();
        } else {
            return distance / y.length();
        }
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

}
