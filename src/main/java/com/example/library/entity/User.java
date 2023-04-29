package com.example.library.entity;

import com.example.library.object.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@SuperBuilder
@Setter
@Getter
@Entity(name = "lib_user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name="phone_number",
            unique = true,
            nullable = false
    )
    private String phoneNumber;

    @Column(name = "birthdate",nullable = false)
    private LocalDate birthdate;

    @Column(name = "reserves")
    private int reserves;

    public static User from(UserDTO user) {
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
