package pl.polsl.olapvocalization.olap.query.queryclauses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SelectionClause implements QueryClause {
    @Override
    public Boolean validate() {
        return null;
    }

    private List<SCN> scnList = new ArrayList<>();

    public void addSCN(SCN scn){
        this.scnList.add(scn);
    }

}
