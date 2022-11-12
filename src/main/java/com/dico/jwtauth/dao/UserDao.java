package com.dico.jwtauth.dao;

import com.dico.jwtauth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends CrudRepository<User, String> {
}
