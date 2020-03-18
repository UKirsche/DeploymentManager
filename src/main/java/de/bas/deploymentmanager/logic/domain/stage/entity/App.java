package de.bas.deploymentmanager.logic.domain.stage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "APP")
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "HOST_ID")
    private Long hostId;

    @Column(name = "IMAGE_ID")
    private Long imageId;

    @Column(name = "PROJECT_IDENFIFIER")
    private String projectIdentifier;

    @Column(name = "PORT")
    private String port;

    @Column(name = "PROJECT_NAME")
    private String name;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createTime;
}
