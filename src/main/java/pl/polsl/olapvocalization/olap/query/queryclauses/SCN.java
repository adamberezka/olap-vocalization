package pl.polsl.olapvocalization.olap.query.queryclauses;

import java.util.List;

public class SCN implements QueryClause {
    @Override
    public Boolean validate() {
        return null;
    }

    private Boolean isNegated;

    private List<String> selectionValues;

}
