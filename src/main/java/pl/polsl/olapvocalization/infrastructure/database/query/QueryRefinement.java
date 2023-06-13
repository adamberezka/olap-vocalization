package pl.polsl.olapvocalization.infrastructure.database.query;

public class QueryRefinement implements Query {

    private RefinementType type;

    @Override
    public boolean isInitial() {
        return false;
    }

    enum RefinementType {
        DRILL,
        ROLLUP,
        SAD,
        ADD,
        DROP,
        REPLACE,

    }

}
