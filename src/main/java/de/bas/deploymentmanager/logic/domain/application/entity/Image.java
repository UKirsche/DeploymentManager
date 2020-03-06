package de.bas.deploymentmanager.logic.domain.application.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Image {
    private Long id;
    private Long applicationId;
    private String tag;
    private String user;
    private LocalDateTime createDate;
    private String image;
    private Integer majorVersion;
    private Integer minorVersion;
    private String incrementalVersion;
    private Integer buildNumber;
    private List<Deployment> deployments;
}
