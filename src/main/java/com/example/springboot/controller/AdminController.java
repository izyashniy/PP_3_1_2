package com.example.springboot.controller;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.RoleServiceImpl;
import com.example.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private RoleServiceImpl roleService;

    private final UserServiceImpl userService;
    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping("/admin")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }



    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user,
                             @RequestParam(name = "role", required = false) String[] roles) {
        List<Role> roleList = new ArrayList<>();
        if (roles != null) {
            for(String s: roles) {
                roleList.add(roleService.findByName(s));
            }
            user.setRoles(roleList);
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user
            , @RequestParam(name = "role", required = false) String[] roles) {
        List<Role> roleList = new ArrayList<>();
        for(String s: roles) {
            roleList.add(roleService.findByName(s));
        }
        user.setRoles(roleList);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
