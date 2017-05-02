package soaiknowauth.repository;

import org.springframework.data.repository.CrudRepository;
import soaiknowauth.model.User;

import java.util.List;

/**
 * Created by SimonaS on 30/04/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findById(long id);
}
