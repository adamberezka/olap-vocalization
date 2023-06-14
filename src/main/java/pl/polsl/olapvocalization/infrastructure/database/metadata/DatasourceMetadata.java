package pl.polsl.olapvocalization.infrastructure.database.metadata;

import lombok.Value;

import java.util.List;

@Value
public class DatasourceMetadata {
    List<CubeMetadata> cubes;
}
