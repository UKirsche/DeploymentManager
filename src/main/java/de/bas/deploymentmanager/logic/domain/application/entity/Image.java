package de.bas.deploymentmanager.logic.domain.application.entity;

import java.time.LocalDateTime;

public class Image {
    private String tag;
    private String user;
    private LocalDateTime createDate;
    private String image;
    private Deployment deploymentEtw;
    private Deployment deploymentInt;
    private Deployment deploymentPrd;

}
