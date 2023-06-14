package pl.polsl.olapvocalization.infrastructure.database.metadata;

import lombok.Value;

@Value
public class CubeDimensionHierarchyMetadata {
    String name;
    CubeDimensionHierarchyLevelMetadata topHierarchyLevel;
}
