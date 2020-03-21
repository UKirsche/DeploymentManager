package de.bas.deploymentmanager.logic.domain.dicd.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * User aus dem Jenkins
 */
@Getter
@Setter
public class User {

    private String name;
    private String loginName;
    private String apiToken;
}
