package com.example.library.object;

import com.example.library.entity.UserEntity;
import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private int reserves;

    public static User from(UserEntity user) {
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .build();
    }
}
