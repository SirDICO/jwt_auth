package com.dico.jwtauth.service;

import com.dico.jwtauth.dao.UserDao;
import com.dico.jwtauth.model.JwtRequest;
import com.dico.jwtauth.model.JwtResponse;
import com.dico.jwtauth.model.User;
import com.dico.jwtauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserService implements UserDetailsService {
   @Autowired
   private UserDao userDao;

   @Autowired
   private JwtUtil jwtUtil;

   @Autowired
   private AuthenticationManager authenticationManager;
   public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
       String userName = jwtRequest.getUserName();
       String userPassword = jwtRequest.getUserPassword();
    return  authenticate(userName, userPassword);
   }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

          User user = userDao.findById(username).get();
    if(user != null){
        return  new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getUserPassword(),
        );
    }
    else
    {

    }

   }

   private Set getAuthorities(User user){
       Set authorities = new HashSet();

       user.getRole().forEach(role ->{
           authorities.add(new SimpleGrantedAuthority("ROLE" + role.getRoleName()));
       });



   }

    private JwtResponse authenticate(String userName, String userPassword) throws Exception {
       try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
       }
       catch(DisabledException e){
           throw new Exception("User is disabled");
       }
       catch(BadCredentialsException e){
           throw new Exception("Bad Credentials from user");
       }
        return null;
    }
}


