package com.example.library.service;

import com.example.library.entity.UserEntity;
import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.User;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository repository;
    public User addUser(User user) {
        user.setReserves(0);
        return User.from(this.repository.save(UserEntity.from(user)));
    }

    public User getUser(long id) {
        return User.from(this.repository.findById(id)
                .orElseThrow(() -> new BaseException("there is no user by this id.", ExceptionsEnum.NOT_EXIST)));
    }

}
