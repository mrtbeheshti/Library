package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.exception.BaseException;
import com.example.library.enums.ExceptionEnum;
import com.example.library.object.UserDTO;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository repository;
    public UserDTO addUser(UserDTO user) {
        if (this.repository.existsByPhoneNumber(user.getPhoneNumber()))
            throw new BaseException("this phone number is already exist.", ExceptionEnum.ALREADY_EXIST);
        return UserDTO.from(this.repository.save(User.from(user)));
    }

    public UserDTO getUser(long id) {
        return UserDTO.from(this.repository.findById(id)
                .orElseThrow(() -> new BaseException("there is no user by this id.", ExceptionEnum.NOT_EXIST)));
    }

    public String authorizeUser(long id) {
        User user = this.repository.findById(id)
                .orElseThrow(() -> new BaseException("there is no user by this id.", ExceptionEnum.NOT_EXIST));
        return "this.repository.save(user).getId();";
    }

}
