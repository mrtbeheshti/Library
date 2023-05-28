package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.enums.RoleEnum;
import com.example.library.enums.SubjectEnum;
import com.example.library.exception.BaseException;
import com.example.library.enums.ExceptionEnum;
import com.example.library.object.UserDTO;
import com.example.library.pojo.log.ThrowableLogPOJO;
import com.example.library.repository.UserRepository;
import com.example.library.security.SocketPrincipal;
import com.example.library.util.LogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    final UserRepository repository;
    @Value("${jwt.secret}")
    private String secret;

//    public UserDTO addUser(UserDTO user) {
////        if (this.repository.existsByPhoneNumber(user.getPhoneNumber()))
////            throw new BaseException("this phone number is already exist.", ExceptionEnum.ALREADY_EXIST);
//        User newUser =  User.from(user, List.of(RoleEnum.ROLE_USER));
//        return UserDTO.from(this.repository.save(newUser));
//    }

    public UserDTO getUser(long id) {

        User user = this.repository.findById(id)
                .orElseThrow(() ->
                {
                    BaseException ex =new BaseException("there is no user by this id.", ExceptionEnum.NOT_EXIST);
                    SocketPrincipal socketPrincipal = (SocketPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    ThrowableLogPOJO logObject = ThrowableLogPOJO.builder()
                            .ssoId(String.valueOf(socketPrincipal.getSsoId()))
                            .details(ex)
                            .message("user not found at *getUser*")
                            .build();
                    LogUtil.error(log,logObject);
                    return ex;
                });
        return UserDTO.from(user);
    }
}
