package com.example.library.object;

import com.example.library.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
public class User {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private int reserves;

    public static User from(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .build();
    }
}
