package com.dico.jwtauth.controller;


import com.dico.jwtauth.model.JwtRequest;
import com.dico.jwtauth.model.JwtResponse;
import com.dico.jwtauth.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private CustomUserService jwtService;


    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
       return jwtService.createJwtToken(jwtRequest);

    }

}
