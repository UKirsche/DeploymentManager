package de.bas.deploymentmanager.logic.domain.stage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "STAGE")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @Enumerated(value = EnumType.STRING)
    private StageEnum name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="stage_id")
    private List<Host> hosts;

}
