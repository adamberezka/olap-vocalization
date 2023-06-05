package pl.polsl.olapvocalization.textinterpretation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static pl.polsl.olapvocalization.textinterpretation.TextInterpreter.tokenizeText;

public class TextInterpretationTest {
    @Test
    public void tokenizationTest(){
        String text = "This is a tokenized text";
        String[] expectedTokenization = {"This", "is", "a", "tokenized", "text"};

        assertArrayEquals(expectedTokenization, tokenizeText(text));
    }

    @Test
    public void levenshteinDistanceTest(){
        assertEquals(3, Levenshtein.distance("kitten", "sitting"));
        assertNotEquals(1.0, Levenshtein.normalizedDistance("kitten", "sitting"));
        assertEquals(0, Levenshtein.normalizedDistance("kitten", "kitten"));
    }
}
