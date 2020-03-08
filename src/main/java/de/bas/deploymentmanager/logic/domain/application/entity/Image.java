package de.bas.deploymentmanager.logic.domain.application.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(name = "IMAGE")
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
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
