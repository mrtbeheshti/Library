package com.example.library.controller;

import com.example.library.object.UserDTO;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping()
    public UserDTO addUser(@RequestBody UserDTO user) {
        return this.service.addUser(user);

    }

    @GetMapping("{id}")
    public UserDTO getUser(@PathVariable long id) {
        return this.service.getUser(id);
    }
}
