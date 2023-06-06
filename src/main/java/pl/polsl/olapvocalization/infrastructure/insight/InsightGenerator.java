package pl.polsl.olapvocalization.infrastructure.insight;

import pl.polsl.olapvocalization.olap.query.QueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InsightGenerator {

    private final List<InsightGeneratorModule> modules = new ArrayList<>();

    public List<Insight> generateInsights(final QueryResult previousQueryResult, final QueryResult currentQueryResult) {
        return modules.stream()
                .map(module -> module.generateInsight(previousQueryResult, currentQueryResult))
                .collect(Collectors.toList());
    }

    public void addModules(final List<InsightGeneratorModule> modules) {
        this.modules.addAll(modules);
    }

    public void addModule(final InsightGeneratorModule module) {
        this.modules.add(module);
    }

}
