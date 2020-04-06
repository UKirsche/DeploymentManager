package de.bas.deploymentmanager.logic.domain.project.entity;

import lombok.Getter;

@Getter
public class Tag {
    private int majorVersion;
    private int minorVersion;
    private int incrementalVersion;
    private int buildNumber;

    public Tag(int majorVersion, int minorVersion, int incrementalVersion, int buildNumber) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.incrementalVersion = incrementalVersion;
        this.buildNumber = buildNumber;
    }

    @Override
    public String toString() {
        return String.format("%s.%s.%s-%s", majorVersion, minorVersion, incrementalVersion, buildNumber);
    }
}
