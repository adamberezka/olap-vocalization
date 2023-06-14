package pl.polsl.olapvocalization.olap.query.queryclauses;

import lombok.Data;

import java.util.List;

@Data
public class SCN implements QueryClause {
    @Override
    public Boolean validate() {
        return null;
    }

    private Boolean isNegated;

    private List<String> selectionValues;
}
