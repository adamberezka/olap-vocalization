package pl.polsl.olapvocalization.olap.query.queryclauses;

import com.sun.tools.javac.util.Pair;

import java.util.List;

public class MeasureClause implements QueryClause {
    @Override
    public Boolean validate() {
        return null;
    }

    private List<Pair<String, String>> values;
}
