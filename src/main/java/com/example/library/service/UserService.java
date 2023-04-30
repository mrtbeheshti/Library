package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.UserDTO;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository repository;
    public UserDTO addUser(UserDTO user) {
        user.setReserves(0);
        return UserDTO.from(this.repository.save(User.from(user)));
    }

    public UserDTO getUser(long id) {
        return UserDTO.from(this.repository.findById(id)
                .orElseThrow(() -> new BaseException("there is no user by this id.", ExceptionsEnum.NOT_EXIST)));
    }

}
