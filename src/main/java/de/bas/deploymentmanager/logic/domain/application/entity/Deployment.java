package de.bas.deploymentmanager.logic.domain.application.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "DEPLOYMENT")
public class Deployment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STAGE_ID")
    private Long stageId;

    @Column(name = "USER")
    private String user;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

}
