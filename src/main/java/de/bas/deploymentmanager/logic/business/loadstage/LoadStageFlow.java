package de.bas.deploymentmanager.logic.business.loadstage;

public interface LoadStageFlow {

    /**
     * Lädt ein Projekt inklusive der Apps sowie der Hosts und Stages
     *
     * @param id ProjectId
     * @return model für die Form
     */
    StageDiagramModel load(Long id);
}
