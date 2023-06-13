package pl.polsl.olapvocalization.infrastructure.database.metadata;

import lombok.Value;

import java.util.List;

@Value
public class CubeDimensionHierarchyMetadata {
    String name;
    List<CubeDimensionHierarchyLevelMetadata> topHierarchyLevels;
    List<AttributeMetadata> attributes;
}
