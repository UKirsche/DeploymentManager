package de.bas.deploymentmanager.logic.domain.stage.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Stage {
    private String name;
    private List<App> applications;

}
