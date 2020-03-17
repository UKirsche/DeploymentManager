package de.bas.deploymentmanager.logic.domain.dicd.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JenkinsParameter {
    public List<JenkinsParameterEntry> parameter;

    public void put(String key, Object value) {
        if (this.parameter == null) {
            this.parameter = new ArrayList<>();
        }
        JenkinsParameterEntry entry = new JenkinsParameterEntry();
        entry.name = key;
        entry.value = value;
        this.parameter.add(entry);
    }
}
