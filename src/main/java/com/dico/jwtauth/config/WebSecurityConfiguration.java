package com.dico.jwtauth.config;

import com.dico.jwtauth.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration  {

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService customUserService;

    public AuthenticationManager authenticationManager(){
     return authenticationManager();
    }

    @Bean
    public  SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
      http.cors();
      http.csrf().disable();
      http.authorizeRequests().antMatchers("").permitAll()
                      .antMatchers(HttpHeaders.ALLOW).permitAll()
                      .anyRequest().authenticated()
                      .and()
                      .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                      .and()
                      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                      ;

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder());
    }
}
