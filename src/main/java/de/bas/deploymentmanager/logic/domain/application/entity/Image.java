package de.bas.deploymentmanager.logic.domain.application.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Image {

    private Long applicationId;
    private String tag;
    private String user;
    private LocalDateTime createDate;
    private String image;
    private Integer majorVersion;
    private Integer minorVersion;
    private String incrementalVersion;
    private Integer buildNumber;
    private Deployment deploymentEtw;
    private Deployment deploymentInt;
    private Deployment deploymentPrd;

}
