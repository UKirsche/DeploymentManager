package de.bas.deploymentmanager.logic.business.loadpipeline;

public interface LoadPipelineFlow {

    /**
     * Lädt ein Projekt inklusive der Images
     * Und eine Information welches Image gerade wo deployed ist
     *
     * @param id ProjectId
     * @return model für die Form
     */
    PipelineFormModel load(Long id);

}
