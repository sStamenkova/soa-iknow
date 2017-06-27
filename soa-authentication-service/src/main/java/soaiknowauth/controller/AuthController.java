package soaiknowauth.controller;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soaiknowauth.model.User;
import soaiknowauth.service.RoleService;
import soaiknowauth.service.SecurityService;
import soaiknowauth.service.UserService;

import java.util.List;

/**
 * Created by SimonaS on 30/04/2017.
 */

@RestController
@RequestMapping(value = "/")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public User registration(@RequestParam("name") String name, @RequestParam("lastName") String lastName,
                             @RequestParam("username") String username, @RequestParam("email") String email,
                             @RequestParam("embg") String embg, @RequestParam("password") String password,
                             @RequestParam("passwordConfirm") String passwordConfirm,
                             @RequestParam("role") String role) {
        return userService.save(name, lastName, username, email, embg, password, passwordConfirm, role);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public User login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.loginUser(username, password);
        securityService.autologin(username, password);
        System.out.println(securityService.findLoggedInUsername());
        return user;
    }

    @RequestMapping(value = "user/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return userService.findById(id);
    }
}
