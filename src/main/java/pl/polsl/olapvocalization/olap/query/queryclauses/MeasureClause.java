package pl.polsl.olapvocalization.olap.query.queryclauses;

import com.sun.tools.javac.util.Pair;

import java.util.List;

public class MeasureClause implements QueryClause {
    @Override
    public Boolean validate() {
        return null;
    }

    private enum MeasureClauseType {
        AGGREGATE,
        COUNT
    }

    private List<Pair<String, String>> values;

    public void addMeasureClausePair(String value1, String value2){
        this.values.add(new Pair<>(value1, value2));
    }
}
