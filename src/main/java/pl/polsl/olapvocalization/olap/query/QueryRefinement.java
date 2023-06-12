package pl.polsl.olapvocalization.olap.query;

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
