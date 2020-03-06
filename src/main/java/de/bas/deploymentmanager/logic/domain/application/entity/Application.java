package de.bas.deploymentmanager.logic.domain.application.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Application {
    private String identifier;
    private String name;
    private String description;
    private List<Image> images;
}
