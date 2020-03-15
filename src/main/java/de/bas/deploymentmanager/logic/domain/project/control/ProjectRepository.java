package de.bas.deploymentmanager.logic.domain.project.control;

import de.bas.deploymentmanager.logic.domain.project.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> getAllProjects();

    Project getByIfentifier(String identifier);

    Project save(Project project);

}
