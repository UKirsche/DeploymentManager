package de.bas.deploymentmanager.frontend.rest.domain;

import de.bas.deploymentmanager.logic.domain.application.entity.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ApplicationModel {
    private Long id;
    private String identifier;
    private String name;
    private String description;
    private List<Image> images;
}
