package pl.polsl.olapvocalization.querybuild;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.polsl.olapvocalization.infrastructure.database.query.InitialQuery;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryBuilder;
import pl.polsl.olapvocalization.infrastructure.database.query.QueryRefinement;
import pl.polsl.olapvocalization.infrastructure.textinterpreter.SimpleTextInterpreter;
import pl.polsl.olapvocalization.infrastructure.textinterpreter.TextInterpreter;
import pl.polsl.olapvocalization.olap.query.queryclauses.GroupByClause;
import pl.polsl.olapvocalization.olap.query.queryclauses.MeasureClause;
import pl.polsl.olapvocalization.olap.query.queryclauses.SCN;
import pl.polsl.olapvocalization.olap.query.queryclauses.SelectionClause;

import java.util.Arrays;
import java.util.Stack;

public class QueryBuildingTest {

    @Test
    public void simpleInterpreterInitialQueryTest(){
        TextInterpreter interpreter = new SimpleTextInterpreter();
        String text = "avg unitSales where year = 2019 groupby region";
        Stack<String> output = new Stack<>();

        output.push("avg");
        output.push("unitSales");
        output.push("WHERE");
        output.push("year");
        output.push("=");
        output.push("2019");
        output.push("GROUP_BY");
        output.push("region");

        Assertions.assertEquals(output, interpreter.getInterpretedText(text));

        Assertions.assertEquals(false, interpreter.isRefinementDeterminer("avg"));
    }

    @Test
    public void simpleInterpreterRefinementTest(){
        TextInterpreter interpreter = new SimpleTextInterpreter();
        String text = "drill region to city";
        Stack<String> output = new Stack<>();

        output.push("DRILL");
        output.push("region");
        output.push("to");
        output.push("city");

        Assertions.assertEquals(output, interpreter.getInterpretedText(text));

        Assertions.assertEquals(true, interpreter.isRefinementDeterminer("DRILL"));

        Assertions.assertEquals(QueryRefinement.RefinementType.DRILL, interpreter.getRefinementType("DRILL"));
    }

    @Test
    public void initialQueryBuildingTest(){
        TextInterpreter interpreter = new SimpleTextInterpreter();
        String text = "avg unitSales where year = 2019 groupby region";
        InitialQuery initialQuery = new InitialQuery();

        MeasureClause measureClause = new MeasureClause();
        SelectionClause selectionClause = new SelectionClause();
        GroupByClause groupByClause = new GroupByClause();

        measureClause.addMeasureClausePair("avg", "unitSales");

        SCN scn = new SCN();
        scn.setSelectionValues(Arrays.asList("year", "=", "2019"));
        selectionClause.addSCN(scn);

        groupByClause.addGroupByAttribute("region");

        initialQuery.setMeasureClause(measureClause);
        initialQuery.setSelectionClause(selectionClause);
        initialQuery.setGroupByClause(groupByClause);

        InitialQuery actualQuery = (InitialQuery) (new QueryBuilder(interpreter).getQuery(text));

        Assertions.assertEquals(measureClause.getValues(), actualQuery.getMeasureClause().getValues());
        Assertions.assertEquals(selectionClause.getScnList().get(0), actualQuery.getSelectionClause().getScnList().get(0));
        Assertions.assertEquals(groupByClause.getAttributes(), actualQuery.getGroupByClause().getAttributes());
    }

    @Test
    public void simpleInitialQueryBuildingTest(){
        TextInterpreter interpreter = new SimpleTextInterpreter();
        String text = "avg unitSales groupby region";
        InitialQuery initialQuery = new InitialQuery();

        MeasureClause measureClause = new MeasureClause();
        GroupByClause groupByClause = new GroupByClause();

        measureClause.addMeasureClausePair("avg", "unitSales");

        groupByClause.addGroupByAttribute("region");

        initialQuery.setMeasureClause(measureClause);
        initialQuery.setGroupByClause(groupByClause);

        InitialQuery actualQuery = (InitialQuery) (new QueryBuilder(interpreter).getQuery(text));

        Assertions.assertEquals(measureClause.getValues(), actualQuery.getMeasureClause().getValues());
        Assertions.assertEquals(groupByClause.getAttributes(), actualQuery.getGroupByClause().getAttributes());
    }

    @Test
    public void verySimpleInitialQueryBuildingTest(){
        TextInterpreter interpreter = new SimpleTextInterpreter();
        String text = "unitSales groupby region";
        InitialQuery initialQuery = new InitialQuery();

        MeasureClause measureClause = new MeasureClause();
        GroupByClause groupByClause = new GroupByClause();

        measureClause.addMeasureClausePair("unitSales", "");

        groupByClause.addGroupByAttribute("region");

        initialQuery.setMeasureClause(measureClause);
        initialQuery.setGroupByClause(groupByClause);

        InitialQuery actualQuery = (InitialQuery) (new QueryBuilder(interpreter).getQuery(text));

        Assertions.assertEquals(measureClause.getValues(), actualQuery.getMeasureClause().getValues());
        Assertions.assertEquals(groupByClause.getAttributes(), actualQuery.getGroupByClause().getAttributes());
    }

    @Test
    public void complexInitialQueryBuildingTest(){
        TextInterpreter interpreter = new SimpleTextInterpreter();
        String text = "avg unitSales where year = 2019 product = milk groupby region";
        InitialQuery initialQuery = new InitialQuery();

        MeasureClause measureClause = new MeasureClause();
        SelectionClause selectionClause = new SelectionClause();
        GroupByClause groupByClause = new GroupByClause();

        measureClause.addMeasureClausePair("avg", "unitSales");

        SCN scn = new SCN();
        scn.setSelectionValues(Arrays.asList("year", "=", "2019"));
        selectionClause.addSCN(scn);
        SCN scn2 = new SCN();
        scn2.setSelectionValues(Arrays.asList("product", "=", "milk"));
        selectionClause.addSCN(scn2);

        groupByClause.addGroupByAttribute("region");

        initialQuery.setMeasureClause(measureClause);
        initialQuery.setSelectionClause(selectionClause);
        initialQuery.setGroupByClause(groupByClause);

        InitialQuery actualQuery = (InitialQuery) (new QueryBuilder(interpreter).getQuery(text));

        Assertions.assertEquals(measureClause.getValues(), actualQuery.getMeasureClause().getValues());
        Assertions.assertEquals(selectionClause.getScnList().get(0), actualQuery.getSelectionClause().getScnList().get(0));
        Assertions.assertEquals(groupByClause.getAttributes(), actualQuery.getGroupByClause().getAttributes());
    }

    @Test
    public void refinementBuildingTest(){
        TextInterpreter interpreter = new SimpleTextInterpreter();
        String text = "drill region to city";
        QueryRefinement queryRefinement = new QueryRefinement();

        // TODO
    }

}
