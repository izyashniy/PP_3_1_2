package com.example.springboot.service;

import com.example.springboot.model.Role;

import java.util.List;

public interface RoleService {
    void add(Role role);
    List<Role> getAllRole();
    Role findByName(String name);
}
