package com.example.library.controller;

import com.example.library.exception.BaseException;
import com.example.library.object.User;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping()
    public User addUser(@RequestBody User user) throws BaseException {
        return this.service.addUser(user);

    }

    @GetMapping("{id}")
    public User getUser(@PathVariable long id) throws BaseException {
        return this.service.getUser(id);
    }
}
