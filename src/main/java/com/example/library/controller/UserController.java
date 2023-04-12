package com.example.library.controller;

import com.example.library.object.Library;
import com.example.library.object.User;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController()
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id){
        return service.getUser(id);
    }



}
