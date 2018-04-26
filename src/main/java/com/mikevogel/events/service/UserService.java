package com.mikevogel.events.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mikevogel.events.models.Role;
import com.mikevogel.events.models.User;
import com.mikevogel.events.repositories.RoleRepository;
import com.mikevogel.events.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    // 3
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Boolean isAdmin(){
        Role role = roleRepository.findByName("ROLE_ADMIN").get(0);
        if(userRepository.findByRolesContains(role).size()>0) {

            return true;
        }else{
            return false;
        }
    }
    
    public List<User> getAll(){
    	return (List<User>) userRepository.findAll();
    }
    
    public Role findRoleByName(String name){
        return roleRepository.findByName(name).get(0);
    }
    
    public void makeUserAdmin(User user){
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }
    
    public User findUserById(Long id) {
    	return userRepository.findById(id).orElse(null);
    }
    
    public void delete(Long id) {
    	User user = userRepository.findById(id).orElse(null);
    	userRepository.delete(user);
    }
}
