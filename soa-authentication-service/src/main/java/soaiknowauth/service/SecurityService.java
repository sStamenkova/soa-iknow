package soaiknowauth.service;

/**
 * Created by SimonaS on 30/04/2017.
 */
public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
