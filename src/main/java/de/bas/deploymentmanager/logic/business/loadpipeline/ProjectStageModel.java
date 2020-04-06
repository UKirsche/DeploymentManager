package de.bas.deploymentmanager.logic.business.loadpipeline;

import de.bas.deploymentmanager.logic.domain.stage.entity.StageEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ProjectStageModel {

    private String image;
    private StageEnum stage;
    private List<String> hosts;
    private LocalDate createTime;

}
