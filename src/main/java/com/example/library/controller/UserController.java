package com.example.library.controller;

import com.example.library.object.User;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController("/users")
public class UserController {

    final UserService service;

    public UserController() {
        this.service = new UserService();
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) {
        return this.service.addUser(user);

    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return this.service.getUser(id);
    }



}
