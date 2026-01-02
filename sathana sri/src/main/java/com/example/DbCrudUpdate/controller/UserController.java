package com.example.DbCrudUpdate.controller;

import com.example.DbCrudUpdate.model.Users;
import com.example.DbCrudUpdate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new Users());
        return "add";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute Users user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        userService.getUserById(id).ifPresentOrElse(
                user -> model.addAttribute("user", user),
                () -> model.addAttribute("user", new Users())
        );
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute Users updatedUser) {
        userService.getUserById(id).ifPresent(user -> {
            user.setName(updatedUser.getName());
            user.setMail(updatedUser.getMail());
            user.setPassword(updatedUser.getPassword());
            userService.saveUser(user);
        });
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
