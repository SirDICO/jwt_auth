package com.dico.jwtauth.service;

import com.dico.jwtauth.dao.RoleDao;
import com.dico.jwtauth.dao.UserDao;
import com.dico.jwtauth.model.Role;
import com.dico.jwtauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
     private   UserDao userDao;
    @Autowired
    private RoleDao roleDoa;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        return userDao.save(user);
    }

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDoa.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDoa.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin");
        adminUser.setUserPassword(getEncodedPassword("admin"));
        adminUser.setUserLastName("Ikenna");
        adminUser.setUserFirstName("Ikenna Divine");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);


        User user = new User();
        user.setUserName("user");
        user.setUserPassword(getEncodedPassword("user"));
        user.setUserLastName("Omega");
        user.setUserFirstName("Ozemenam Divine");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);

    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
