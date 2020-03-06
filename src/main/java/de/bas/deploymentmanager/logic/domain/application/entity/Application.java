package de.bas.deploymentmanager.logic.domain.application.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@Builder
public class Application {
    private Long id;
    private String identifier;
    private String name;
    private String description;
    @Transient
    private List<Image> images;
}
