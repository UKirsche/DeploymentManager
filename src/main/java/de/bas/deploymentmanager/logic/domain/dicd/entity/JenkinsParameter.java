package de.bas.deploymentmanager.logic.domain.dicd.entity;

import java.util.HashMap;

public class JenkinsParameter {
    public HashMap<String, String> parameter;

    public void put(String key, String value) {
        if (this.parameter == null) {
            this.parameter = new HashMap<>();
        }
        this.parameter.put(key, value);
    }
}
