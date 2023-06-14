package pl.polsl.olapvocalization.olap.query.queryclauses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupByClause implements QueryClause {
    @Override
    public Boolean validate() {
        return null;
    }

    private List<String> attributes = new ArrayList<>();

    public void addGroupByAttribute(String attribute){
        this.attributes.add(attribute);
    }

}
