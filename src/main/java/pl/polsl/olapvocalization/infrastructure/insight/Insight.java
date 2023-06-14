package pl.polsl.olapvocalization.infrastructure.insight;

import lombok.Value;

@Value
public class Insight {

    String description;

    Double coverage;
    Double interestingness;
    Double vocalizationCost;

}
