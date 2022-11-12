package com.dico.jwtauth.dao;

import com.dico.jwtauth.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao  extends CrudRepository<Role, String> {
}
