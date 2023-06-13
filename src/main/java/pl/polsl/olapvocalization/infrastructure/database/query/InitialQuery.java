package pl.polsl.olapvocalization.infrastructure.database.query;

import lombok.Data;
import pl.polsl.olapvocalization.olap.query.queryclauses.GroupByClause;
import pl.polsl.olapvocalization.olap.query.queryclauses.MeasureClause;
import pl.polsl.olapvocalization.olap.query.queryclauses.SelectionClause;

@Data
public class InitialQuery implements Query {
    @Override
    public boolean isInitial() {
        return true;
    }

    private MeasureClause measureClause;
    private SelectionClause selectionClause;
    private GroupByClause groupByClause;

}
