package pl.polsl.olapvocalization.olap.query;

public class QueryRefinement {
    enum RefinementType {
        DRILL,
        ROLLUP,
        SAD,
        ADD,
        DROP,
        REPLACE,
    }

    private RefinementType type;

}
