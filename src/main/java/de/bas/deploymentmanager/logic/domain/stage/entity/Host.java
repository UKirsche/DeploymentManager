package de.bas.deploymentmanager.logic.domain.stage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "HOST")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STAGE_ID")
    private Long stageId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "host_id")
    private List<App> applications;
}
