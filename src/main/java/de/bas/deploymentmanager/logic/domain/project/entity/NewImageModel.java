package de.bas.deploymentmanager.logic.domain.project.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewImageModel {
    private String user;
    private String image;
    private String commit;
    private String version;
}
