package de.bas.deploymentmanager.logic.domain.stage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
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

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private URL url;

    @OneToMany
    private List<App> applications;
}
