package de.bas.deploymentmanager.logic.domain.project.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BUILD_JOB")
    private String buildJob;

    @Column(name = "DEPLOY_JOB")
    private String deployJob;

    @Column(name = "DESCRIPTION")
    private String description;

    @Transient
    private List<Image> images;

    @Column(name = "IMAGE_SYNC")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String imageSyncValue;

    public ImageSync getImageSync() {
        return new ImageSync(imageSyncValue);
    }

    public void setImageSync(ImageSync imageSync) {
        imageSyncValue = imageSync.getPersistedValue();
    }

    public boolean isPersisted() {
        return id != null;
    }
}
