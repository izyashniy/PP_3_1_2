package com.example.springboot.dao;

import com.example.springboot.model.User;

import java.util.List;

public interface UserDAO {
    public User findById(Long id);
    public List<User> findAll();
    public void saveUser(User user);
    public void deleteById(Long id);
    public void updateUser(User user);
    public User findByUsername(String username);
}
