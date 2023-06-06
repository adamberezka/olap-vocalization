package pl.polsl.olapvocalization.infrastructure.vocalization;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInsightVocalizator implements InsightVocalizator {

    @Override
    public void vocalize(final String insightDescription) {
        log.info("INSIGHT:   " + insightDescription);
    }
}
