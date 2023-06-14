package pl.polsl.olapvocalization.infrastructure.database;

import lombok.Getter;
import pl.polsl.olapvocalization.infrastructure.database.query.InitialQuery;
import pl.polsl.olapvocalization.infrastructure.database.query.Query;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryRefinement;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryResult;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserSession {

    private InitialQuery initialQuery;
    private InitialQuery currentQuery;
    private final List<QueryRefinement> refinementHistory = new ArrayList<>();

    private QueryResult previousResult;
    private QueryResult currentResult;


    public void updateSession(final Query query) {
        if (query.isInitial()) {
            setInitialQuery((InitialQuery) query);
        } else {
            addRefinement((QueryRefinement) query);
        }
    }

    private void setInitialQuery(final InitialQuery query) {
        initialQuery = query;
    }

    private void addRefinement(final QueryRefinement refinement) {
        refinementHistory.add(refinement);
    }

    public void updateResult(final QueryResult newQueryResult) {
        previousResult = currentResult;
        currentResult = newQueryResult;
    }

}
