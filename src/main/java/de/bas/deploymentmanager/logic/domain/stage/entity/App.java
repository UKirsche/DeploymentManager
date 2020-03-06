package de.bas.deploymentmanager.logic.domain.stage.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class App {
    private String name;
    private String image;
    private String version;
    private LocalDateTime createTime;
}
