package com.example.library.object;

import com.example.library.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class User {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private int reserves;

    public static User from(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .reserves(user.getReserves())
                .build();
    }
}
