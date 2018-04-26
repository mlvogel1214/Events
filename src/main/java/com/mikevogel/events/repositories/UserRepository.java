package com.mikevogel.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mikevogel.events.models.Role;
import com.mikevogel.events.models.User;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	List<User> findByRolesContains(Role role);
	
}
