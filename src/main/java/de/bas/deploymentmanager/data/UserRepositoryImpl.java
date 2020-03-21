package de.bas.deploymentmanager.data;

import de.bas.deploymentmanager.logic.domain.dicd.control.UserRepository;
import de.bas.deploymentmanager.logic.domain.dicd.entity.User;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User getUserByLoginName() {
        User user = new User();
        user.setLoginName("test");
        user.setApiToken("11202eb995f84243e272ba44a025461455");
        return user;
    }
}
