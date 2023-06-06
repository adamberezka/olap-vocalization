package pl.polsl.olapvocalization.infrastructure;

import lombok.Getter;
import pl.polsl.olapvocalization.olap.query.Query;
import pl.polsl.olapvocalization.olap.query.QueryRefinement;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserSession {

    private Query initialQuery;
    private Query currentQuery;
    private final List<QueryRefinement> refinementHistory = new ArrayList<>();

    public void setInitialQuery(Query query) {
        initialQuery = query;
    }

    public void addRefinement(QueryRefinement refinement) {
        refinementHistory.add(refinement);

    }

}
