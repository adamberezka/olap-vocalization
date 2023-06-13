package pl.polsl.olapvocalization.infrastructure.database.metadata;

import lombok.Value;

import java.util.List;

@Value
public class CubeDimensionMetadata {
    String name;
    List<CubeDimensionHierarchyMetadata> hierarchies;
    List<AttributeMetadata> attributes;
}
