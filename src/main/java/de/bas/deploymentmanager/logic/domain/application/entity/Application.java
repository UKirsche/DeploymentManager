package de.bas.deploymentmanager.logic.domain.application.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "APPLICATION")
public class Application {

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

}
