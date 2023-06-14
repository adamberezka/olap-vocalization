package pl.polsl.olapvocalization.infrastructure.textinterpreter;

import org.springframework.util.CollectionUtils;
import pl.polsl.olapvocalization.infrastructure.database.metadata.MetadataRepository;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryRefinement;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Stream;

public class SimpleTextInterpreter implements TextInterpreter {

    MetadataRepository metadataRepository;

    private enum CustomTokens {
        WHERE,
        GROUP_BY,
        DRILL,
        ROLLUP,
        SLICE,
        ADD,
        DROP,
        REPLACE,
        NEGATION
    }

    private Optional<String> getCustomToken(String token){
        Stream<String> customTokens = Arrays.asList(
                CustomTokens.WHERE.toString(),
                CustomTokens.GROUP_BY.toString(),
                CustomTokens.DRILL.toString(),
                CustomTokens.ROLLUP.toString(),
                CustomTokens.SLICE.toString(),
                CustomTokens.ADD.toString(),
                CustomTokens.DROP.toString(),
                CustomTokens.REPLACE.toString(),
                CustomTokens.NEGATION.toString()
        ).stream();

        Optional<String> customToken = customTokens.filter(ct -> ct.toLowerCase().replace("_", "").equals(token.toLowerCase())).findFirst();

        return customToken;
    }

    @Override
    public Stack<String> getInterpretedText(String input) {
        Stack<String> resultStack = new Stack<>();

        for (String token : input.split(" ")) {
            Optional<String> customToken = getCustomToken(token);

            if (customToken.isPresent()) {
                resultStack.add(customToken.get());
            } else {
                resultStack.add(token);
            }

        }

        return resultStack;
    }

    @Override
    public String getMeasureClauseTerminatorToken() {
        return CustomTokens.WHERE.toString();
    }

    @Override
    public String getSelectionClauseTerminatorToken() {
        return CustomTokens.GROUP_BY.toString();
    }

    @Override
    public String getNegationToken() {
        return CustomTokens.NEGATION.toString();
    }

    @Override
    public Boolean isRefinementDeterminer(String input) {
        Stream<String> refinementKeyWords = Arrays.asList(
                CustomTokens.DRILL.toString(),
                CustomTokens.ROLLUP.toString(),
                CustomTokens.SLICE.toString(),
                CustomTokens.ADD.toString(),
                CustomTokens.DROP.toString(),
                CustomTokens.REPLACE.toString()
        ).stream();

        if (refinementKeyWords.anyMatch(keyWord -> keyWord.equals(input))) {
            return true;
        }

        return false;
    }

    @Override
    public QueryRefinement.RefinementType getRefinementType(String input) {
        switch (input) {
            case "DRILL":
                return QueryRefinement.RefinementType.DRILL;
            case "ROLLUP":
                return QueryRefinement.RefinementType.ROLLUP;
            case "SLICE":
                return QueryRefinement.RefinementType.SLICE;
            case "ADD":
                return QueryRefinement.RefinementType.ADD;
            case "DROP":
                return QueryRefinement.RefinementType.DROP;
            case "REPLACE":
                return QueryRefinement.RefinementType.REPLACE;
            default:
                return null;
        }
    }
}
