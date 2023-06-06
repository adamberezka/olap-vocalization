package pl.polsl.olapvocalization.olap.metadata;

import lombok.Value;

@Value
public class CubeDimensionHierarchyMetadata {
    String name;
    CubeDimensionHierarchyLevelMetadata topHierarchyLevel;
}
