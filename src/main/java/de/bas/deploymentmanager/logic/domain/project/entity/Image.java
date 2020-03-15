package de.bas.deploymentmanager.logic.domain.project.entity;

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

    @Column(name = "PROJECT_ID")
    private Long projectId;

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

    public String getImageWithTag() {
        return String.format("%s:%s", image, tag);
    }

    @OneToMany()
    private List<Deployment> deployments;
}
