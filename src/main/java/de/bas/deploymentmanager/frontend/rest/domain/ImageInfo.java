package de.bas.deploymentmanager.frontend.rest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageInfo {
    private String user;
    private String image;
    private Integer majorVersion;
    private Integer minorVersion;
    private String incrementalVersion;
}
