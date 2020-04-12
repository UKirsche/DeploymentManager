package de.bas.deploymentmanager.logic.domain.project.entity.exception;

public class TagNotValidException extends Exception {
    public TagNotValidException(String tag) {
        super("Tag " + tag + " ist nicht gültig");
    }
}
