package com.example.library.controller;

import com.example.library.exception.BaseException;
import com.example.library.object.User;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("users")
public class UserController {

    final UserService service;

    public UserController() {
        this.service = new UserService();
    }

    @PostMapping()
    public User addUser(@RequestBody User user) throws BaseException {
        return this.service.addUser(user);

    }

    @GetMapping("{id}")
    public User getUser(@PathVariable long id) throws BaseException {
        return this.service.getUser(id);
    }
}
