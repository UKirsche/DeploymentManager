package de.bas.deploymentmanager.logic.business.loadproject;

import de.bas.deploymentmanager.logic.domain.project.entity.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProjectFormModel {

    private Project project;
    private List<ProjectStageModel> deployedOn;
}
