package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.enums.RoleEnum;
import com.example.library.exception.BaseException;
import com.example.library.enums.ExceptionEnum;
import com.example.library.object.UserDTO;
import com.example.library.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository repository;
    @Value("${jwt.secret}")
    private String secret;
    public UserDTO addUser(UserDTO user) {
        if (this.repository.existsByPhoneNumber(user.getPhoneNumber()))
            throw new BaseException("this phone number is already exist.", ExceptionEnum.ALREADY_EXIST);
        User newUser =  User.from(user);
        newUser.setRoles(Collections.singletonList(RoleEnum.ROLE_USER));
        return UserDTO.from(this.repository.save(newUser));
    }

    public UserDTO getUser(long id) {
        return UserDTO.from(this.repository.findById(id)
                .orElseThrow(() -> new BaseException("there is no user by this id.", ExceptionEnum.NOT_EXIST)));
    }

    public String authorizeUser(long id) {
        if(!this.repository.existsById(id))
            throw new BaseException("there is no user by this id.", ExceptionEnum.NOT_EXIST);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return "Bearer " + Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .claim("user_id", id)
                .setSubject("jane")
                .setId(UUID.randomUUID().toString())
                .compact();
    }

}
