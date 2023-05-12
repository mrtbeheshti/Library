package com.example.library.entity;

import com.example.library.enums.RoleEnum;
import com.example.library.object.UserDTO;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

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
    @NotNull
    @Column(name="phone_number",
            unique = true,
            nullable = false
    )
    private String phoneNumber;

    @Column(name = "birthdate",nullable = false)
    private LocalDate birthdate;

    @Column(name = "reserves")
    private int reserves;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "roles")
    @Builder.Default
    private Collection<RoleEnum> roles=new HashSet<>();

    public static User from(UserDTO user, Collection<RoleEnum> roles) {

        return User.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .reserves(user.getReserves())
                .roles(roles)
                .build();
    }
}
