package com.example.library.controller;

import com.example.library.object.UserDTO;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_BOOKER')")
    public UserDTO getUser(@PathVariable long id) {
        return this.service.getUser(id);
    }

    @PostMapping("authorize/{id}")
    public String authorizeUser(@PathVariable long id) {
        return this.service.authorizeUser(id);
    }
}
