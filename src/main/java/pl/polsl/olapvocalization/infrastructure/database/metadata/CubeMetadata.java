package pl.polsl.olapvocalization.infrastructure.database.metadata;

import lombok.Value;

import java.util.List;

@Value
public class CubeMetadata {
    String name;
    List<CubeDimensionMetadata> dimensions;
    List<CubeMeasureMetadata> measures;
}
