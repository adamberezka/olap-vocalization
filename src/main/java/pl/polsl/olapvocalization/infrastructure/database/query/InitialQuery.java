package pl.polsl.olapvocalization.infrastructure.database.query;

public class InitialQuery implements Query {
    @Override
    public boolean isInitial() {
        return true;
    }
}
