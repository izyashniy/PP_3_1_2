package com.example.springboot.service;

import com.example.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User findById(Long id);
    public List<User> findAll();
    public void saveUser(User user);
    public void deleteById(Long id);
    public void updateUser(User user);
    public User findByUsername(String username);
}
