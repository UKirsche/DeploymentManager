package de.bas.deploymentmanager.logic.domain.dicd.control;

import de.bas.deploymentmanager.logic.domain.dicd.entity.User;

public interface UserRepository {

    User getUserByLoginName();
}
