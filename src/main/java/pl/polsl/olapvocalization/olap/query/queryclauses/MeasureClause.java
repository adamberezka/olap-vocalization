package pl.polsl.olapvocalization.olap.query.queryclauses;

import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Data
public class MeasureClause implements QueryClause {
    @Override
    public Boolean validate() {
        return null;
    }

    private enum MeasureClauseType {
        AGGREGATE,
        COUNT
    }

    private List<Pair<String, String>> values = new ArrayList<>();

    public void addMeasureClausePair(String value1, String value2){
        this.values.add(new Pair<>(value1, value2));
    }

    @Value
    public static class Pair<T1, T2> {
        T1 value1;
        T2 value2;
    }

}
