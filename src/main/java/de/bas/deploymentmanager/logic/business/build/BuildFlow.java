package de.bas.deploymentmanager.logic.business.build;

public interface BuildFlow {

    /**
     * Führt den Buildjob eines Projects aus
     * @param ProjectId
     */
    void build(Long ProjectId);
}
