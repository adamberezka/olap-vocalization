package pl.polsl.olapvocalization.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.polsl.olapvocalization.infrastructure.database.UserSession;
import pl.polsl.olapvocalization.infrastructure.VOOLProcessor;
import pl.polsl.olapvocalization.infrastructure.database.QueryExecutor;
import pl.polsl.olapvocalization.infrastructure.database.logging.OnlyLoggingQueryExecutor;
import pl.polsl.olapvocalization.infrastructure.database.oracle.OracleQueryExecutor;
import pl.polsl.olapvocalization.infrastructure.input.ConsoleInputManager;
import pl.polsl.olapvocalization.infrastructure.input.InputManager;
import pl.polsl.olapvocalization.infrastructure.insight.InsightGenerator;
import pl.polsl.olapvocalization.infrastructure.insight.InsightGeneratorModule;
import pl.polsl.olapvocalization.infrastructure.textinterpreter.COOLInterpreter;
import pl.polsl.olapvocalization.infrastructure.textinterpreter.SimpleTextInterpreter;
import pl.polsl.olapvocalization.infrastructure.textinterpreter.TextInterpreter;
import pl.polsl.olapvocalization.infrastructure.validator.QueryValidator;
import pl.polsl.olapvocalization.infrastructure.validator.SimpleQueryValidator;
import pl.polsl.olapvocalization.infrastructure.vocalization.InsightVocalizator;
import pl.polsl.olapvocalization.infrastructure.vocalization.LoggingInsightVocalizator;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryBuilder;

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

    @Bean
    @ConditionalOnProperty(name = "olap.vocalization.input-manager", havingValue = "CONSOLE")
    public ConsoleInputManager consoleInputManager() {
        return new ConsoleInputManager();
    }

    @Bean
    @ConditionalOnProperty(name = "olap.vocalization.text-interpreter", havingValue = "SIMPLE")
    public SimpleTextInterpreter simpleTextInterpreter() {
        return new SimpleTextInterpreter();
    }

    @Bean
    @ConditionalOnProperty(name = "olap.vocalization.text-interpreter", havingValue = "COOL")
    public COOLInterpreter coolInterpreter() {
        return new COOLInterpreter();
    }

    @Bean
    @ConditionalOnProperty(name = "olap.vocalization.query-validator", havingValue = "SIMPLE")
    public SimpleQueryValidator simpleQueryValidator() {
        return new SimpleQueryValidator();
    }

    @Bean
    @ConditionalOnProperty(name = "olap.vocalization.query-executor", havingValue = "LOGGING")
    public QueryExecutor loggingQueryExecutor() {
        return new OnlyLoggingQueryExecutor();
    }

    @Bean
    @ConditionalOnProperty(name = "olap.vocalization.insight-vocalizator", havingValue = "LOGGING")
    public InsightVocalizator loggingInsightVocalizator() {
        return new LoggingInsightVocalizator();
    }

}
