package de.bas.deploymentmanager.logic.domain.project.entity;

import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
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

    @Column(name = "STAGE")
    @Enumerated(EnumType.STRING)
    private StageEnum stage;

    @Column(name = "USER_")
    private String user;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

}
