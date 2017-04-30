package soaiknowauth.service;

import org.springframework.data.repository.CrudRepository;
import soaiknowauth.model.User;

/**
 * Created by SimonaS on 30/04/2017.
 */
public interface UserService {
    User save(String name, String lastName, String username, String email, String embg, String password, String passwordConfirm, String role);
    User findByUsername(String username);
    User loginUser(String username, String password);
    void deleteUser(long id);
}
