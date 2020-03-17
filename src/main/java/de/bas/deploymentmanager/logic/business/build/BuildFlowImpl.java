package de.bas.deploymentmanager.logic.business.build;

import de.bas.deploymentmanager.logic.domain.dicd.boundary.CiCdService;
import de.bas.deploymentmanager.logic.domain.project.boundary.ProjectService;
import de.bas.deploymentmanager.logic.domain.project.entity.Project;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BuildFlowImpl implements BuildFlow {

    private final CiCdService ciCdService;
    private final ProjectService projectService;


    @Inject
    public BuildFlowImpl(CiCdService ciCdService, ProjectService projectService) {
        this.ciCdService = ciCdService;
        this.projectService = projectService;
    }

    @Override
    public void build(Long projectId) {
        Project project = projectService.getProject(projectId);
        ciCdService.buildImage(project.getBuildJob());
    }
}
