package com.example.library.service;

import com.example.library.object.User;
import org.springframework.stereotype.Service;

import static com.example.library.object.Library.users;

@Service
public class UserService {

    public User addUser(User user){
        user.setReserves(0);
        if (users.stream().anyMatch(u -> u.getId() == user.getId()))
            throw new RuntimeException( "this id already exists.");
        if (users.stream().anyMatch(
                u -> (u.getFirstName().equals(user.getFirstName()) &&
                        u.getLastName().equals(user.getLastName()))))
            throw new RuntimeException( String.format("User '%s %s' already exists.", user.getFirstName(), user.getLastName()));
        users.add(user);
        return user;
    }

    public User getUser(long id){
        return  users.stream().filter(user -> user.getId()==id).toList().get(0);
    }

}
