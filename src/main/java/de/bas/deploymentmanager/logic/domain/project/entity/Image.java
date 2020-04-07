package de.bas.deploymentmanager.logic.domain.project.entity;

import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import lombok.AccessLevel;
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
    @Setter(AccessLevel.NONE)
    private String tag;

    @Column(name = "CREATE_USER")
    private String user;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "MAJOR_VERSION")
    @Setter(AccessLevel.NONE)
    private Integer majorVersion;

    @Column(name = "MINOR_VERSION")
    @Setter(AccessLevel.NONE)
    private Integer minorVersion;

    @Column(name = "INCREMENTAL_VERSION")
    @Setter(AccessLevel.NONE)
    private Integer incrementalVersion;

    @Column(name = "BUILD_NUMBER")
    @Setter(AccessLevel.NONE)
    private Integer buildNumber;


    @Column(name = "COMMIT_HASH")
    private String commit;

    public String getImageWithTag() {
        return String.format("%s:%s", image, tag);
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private List<Deployment> deployments;

    public Deployment getDeployment(StageEnum stage) {
        if (deployments != null) {
            return deployments.stream().filter(d -> d.getStage().equals(stage)).findFirst().orElse(null);
        }
        return null;
    }

    public String setTag(Tag tagVo) {
        majorVersion = tagVo.getMajorVersion();
        minorVersion = tagVo.getMinorVersion();
        incrementalVersion = tagVo.getIncrementalVersion();
        buildNumber = tagVo.getBuildNumber();
        tag = tagVo.toString();
        return tag;
    }
}
