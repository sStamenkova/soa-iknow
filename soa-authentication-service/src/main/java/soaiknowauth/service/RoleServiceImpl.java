package soaiknowauth.service;

/**
 * Created by SimonaS on 12/04/2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soaiknowauth.model.Role;
import soaiknowauth.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createRoleIfNotFound(Role role) {

        List<Role> r = roleRepository.findByName(role.getName());

        if(r.isEmpty()) {
            roleRepository.save(role);
        }
    }
}
