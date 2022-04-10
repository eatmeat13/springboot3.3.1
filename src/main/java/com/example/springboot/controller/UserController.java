package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserSer userService;

    @Autowired
    public UserController(UserSer userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("userList", userService.getList());
        return "users";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping
    public String newPerson(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("{id}")
    public String updateUser(Model model, @PathVariable("id") int id) {

        model.addAttribute("user", userService.getUser(id));
        return "update_user";
    }

    @PatchMapping("{id}")
    public String userUpdate(@PathVariable("id") int id,@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
