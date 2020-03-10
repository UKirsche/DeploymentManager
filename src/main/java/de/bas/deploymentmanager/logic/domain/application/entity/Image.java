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

    @Column(name = "APPLICATION_ID")
    private Long applicationId;

    @Column(name = "TAG")
    private String tag;

    @Column(name = "CREATE_USER")
    private String user;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "MAJOR_VERSION")
    private Integer majorVersion;

    @Column(name = "MINOR_VERSION")
    private Integer minorVersion;

    @Column(name = "BUILD_NUMBER")
    private Integer buildNumber;

    @OneToMany()
    private List<Deployment> deployments;
}
