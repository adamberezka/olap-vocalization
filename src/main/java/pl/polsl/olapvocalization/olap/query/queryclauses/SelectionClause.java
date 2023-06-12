package pl.polsl.olapvocalization.olap.query.queryclauses;

import java.util.List;

public class SelectionClause implements QueryClause {
    @Override
    public Boolean validate() {
        return null;
    }

    private List<SCN> scnList;

}
