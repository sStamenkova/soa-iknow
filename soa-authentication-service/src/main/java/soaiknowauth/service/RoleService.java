package soaiknowauth.service;

import soaiknowauth.model.Role;

/**
 * Created by SimonaS on 30/04/2017.
 */
public interface RoleService {
    void createRoleIfNotFound(Role role);
}
