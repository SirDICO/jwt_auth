package com.dico.jwtauth.controller;


import com.dico.jwtauth.model.User;
import com.dico.jwtauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
   @Autowired
   private UserService userService;

   @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody  User user){
    return  userService.registerNewUser(user);
    }


    @PostConstruct
    public void initRolesAndUser(){
       userService.initRoleAndUser();
    }
}
