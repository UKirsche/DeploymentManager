package de.bas.deploymentmanager.logic.domain.application.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Deployment {
    private Long id;
    private Long stageId;
    private String user;
    private LocalDateTime createTime;

}
