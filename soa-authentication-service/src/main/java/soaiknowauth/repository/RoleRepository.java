package soaiknowauth.repository;

import org.springframework.data.repository.CrudRepository;
import soaiknowauth.model.Role;

import java.util.List;

/**
 * Created by SimonaS on 30/04/2017.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByName(String name);
}
