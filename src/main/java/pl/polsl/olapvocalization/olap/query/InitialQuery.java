package pl.polsl.olapvocalization.olap.query;

public class InitialQuery implements Query {
    @Override
    public boolean isInitial() {
        return true;
    }
}
