package de.bas.deploymentmanager.frontend.rest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageModel {
    private String user;
    private String image;
    private String version;
    private String incrementalVersion;
}
