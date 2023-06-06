package pl.polsl.olapvocalization.olap.metadata;

import lombok.Value;

import java.util.List;

@Value
public class CubeDimensionHierarchyLevelMetadata {
    String name;
    List<CubeDimensionHierarchyLevelMetadata> childLevels;
}
