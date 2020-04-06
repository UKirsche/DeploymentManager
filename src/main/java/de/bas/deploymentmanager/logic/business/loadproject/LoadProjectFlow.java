package de.bas.deploymentmanager.logic.business.loadproject;

public interface LoadProjectFlow {

    /**
     * Lädt ein Projekt inklusive der Images
     *
     * @param id ProjectId
     * @return model für die Form
     */
    ProjectFormModel load(Long id);

    /**
     * Speichert ein neues Projekt und lädt ein neues Model
     *
     * @param model .
     * @return Model für die Form
     */
    ProjectFormModel save(ProjectFormModel model);
}
