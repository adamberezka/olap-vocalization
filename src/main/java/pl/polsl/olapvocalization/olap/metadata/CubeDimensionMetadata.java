package pl.polsl.olapvocalization.olap.metadata;

import lombok.Value;

import java.util.List;

@Value
public class CubeDimensionMetadata {
    String name;
    List<CubeDimensionHierarchyMetadata> hierarchies;
}
