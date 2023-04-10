package com.example.library.controller;

import com.example.library.object.Library;
import com.example.library.object.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.library.object.Library.users;

@RestController()
public class UserController {
//    public static List<User> users = new ArrayList<>();

    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        user.setReserves(0);
        if (users.stream().anyMatch(u -> u.getId() == user.getId()))
            return "this id already exists.";
        if (users.stream().anyMatch(
                u -> (u.getFirstName().equals(user.getFirstName()) &&
                        u.getLastName().equals(user.getLastName()))))
            return String.format("User '%s %s' already exists.", user.getFirstName(), user.getLastName());
        users.add(user);
        return String.format("User '%s %s' successfully added to library.", user.getFirstName(), user.getLastName());
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id){
        return  users.stream().filter(user -> user.getId()==id).toList().get(0);
    }



}
