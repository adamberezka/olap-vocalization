package pl.polsl.olapvocalization.infrastructure.database.query;

import lombok.Data;

import java.util.List;

@Data
public class QueryRefinement implements Query {
    public enum RefinementType {
        DRILL,
        ROLLUP,
        SLICE,
        ADD,
        DROP,
        REPLACE,
    }
    @Override
    public boolean isInitial() {
        return false;
    }

    private RefinementType type;

    private List<String> oldClause;
    private List<String> newClause;
}
