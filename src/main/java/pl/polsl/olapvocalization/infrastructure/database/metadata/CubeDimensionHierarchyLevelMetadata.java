package pl.polsl.olapvocalization.infrastructure.database.metadata;

import lombok.Value;

import java.util.List;

@Value
public class CubeDimensionHierarchyLevelMetadata {
    String name;
    Integer depth;
    List<AttributeMetadata> attributes;
}
