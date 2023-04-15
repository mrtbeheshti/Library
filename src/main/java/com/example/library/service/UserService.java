package com.example.library.service;

import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.User;
import org.springframework.stereotype.Service;

import static com.example.library.object.Library.users;

@Service
public class UserService {

    public User addUser(User user) {
        user.setReserves(0);
        if (users.stream().anyMatch(u -> u.getId() == user.getId()))
            throw new BaseException("this id already exists.", ExceptionsEnum.ALREADY_EXIST);
        if (users.stream().anyMatch(
                u -> (u.getFirstName().equals(user.getFirstName()) &&
                        u.getLastName().equals(user.getLastName()))))
            throw new BaseException(String.format("User '%s %s' already exists.", user.getFirstName(),
                    user.getLastName()), ExceptionsEnum.ALREADY_EXIST);
        users.add(user);
        return user;
    }

    public User getUser(long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(
                () -> new BaseException("there is no user by this id.", ExceptionsEnum.NOT_EXIST));
    }

}
