package pl.polsl.olapvocalization.infrastructure;

import lombok.Getter;
import pl.polsl.olapvocalization.olap.query.Query;
import pl.polsl.olapvocalization.olap.query.QueryRefinement;
import pl.polsl.olapvocalization.olap.query.QueryResult;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserSession {

    private Query initialQuery;
    private Query currentQuery;
    private final List<QueryRefinement> refinementHistory = new ArrayList<>();

    private QueryResult previousResult;
    private QueryResult currentResult;


    public void setInitialQuery(Query query) {
        initialQuery = query;
    }

    public void addRefinement(QueryRefinement refinement) {
        refinementHistory.add(refinement);
    }

    public void updateResult(final QueryResult newQueryResult) {
        previousResult = currentResult;
        currentResult = newQueryResult;
    }

}
