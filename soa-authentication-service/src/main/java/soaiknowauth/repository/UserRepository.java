package soaiknowauth.repository;

import org.springframework.data.repository.CrudRepository;
import soaiknowauth.model.User;

/**
 * Created by SimonaS on 30/04/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
