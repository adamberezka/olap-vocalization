package pl.polsl.olapvocalization.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.polsl.olapvocalization.infrastructure.UserSession;
import pl.polsl.olapvocalization.infrastructure.VOOLProcessor;
import pl.polsl.olapvocalization.infrastructure.database.QueryExecutor;
import pl.polsl.olapvocalization.infrastructure.input.InputManager;
import pl.polsl.olapvocalization.infrastructure.insight.InsightGenerator;
import pl.polsl.olapvocalization.infrastructure.insight.InsightGeneratorModule;
import pl.polsl.olapvocalization.infrastructure.textinterpreter.TextInterpreter;
import pl.polsl.olapvocalization.infrastructure.validator.QueryValidator;
import pl.polsl.olapvocalization.infrastructure.vocalization.InsightVocalizator;
import pl.polsl.olapvocalization.olap.query.QueryBuilder;

import java.util.List;

@Configuration
public class OlapVocalizationApplicationConfiguration {

    @Bean
    public VOOLProcessor voolProcessor(final InputManager inputManager,
                                       final TextInterpreter textInterpreter,
                                       final QueryValidator queryValidator,
                                       final QueryExecutor queryExecutor,
                                       final List<InsightGeneratorModule> insightGeneratorModules,
                                       final InsightVocalizator insightVocalizator) {

        final InsightGenerator insightGenerator = new InsightGenerator();
        insightGenerator.addModules(insightGeneratorModules);

        return new VOOLProcessor(
                new UserSession(),
                inputManager,
                new QueryBuilder(textInterpreter),
                queryValidator,
                queryExecutor,
                insightGenerator,
                insightVocalizator
        );
    }



}
