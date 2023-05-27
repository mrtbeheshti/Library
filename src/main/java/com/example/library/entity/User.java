package com.example.library.entity;

import com.example.library.enums.RoleEnum;
import com.example.library.object.UserDTO;
import javax.persistence.*;

import com.example.library.vo.auth.PodUserInfoVo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Collection;
import java.util.HashSet;

@SuperBuilder
@Setter
@Getter
@Entity(name = "lib_user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Column(name = "sso_id")
    private long ssoId;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;


    @Column(name = "reserves")
    private int reserves;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "roles")
    @Builder.Default
    private Collection<RoleEnum> roles=new HashSet<>();

    public void map(UserDTO user){
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
//        this.phoneNumber=user.getPhoneNumber();
//        this.birthdate=user.getBirthdate();
        this.reserves=user.getReserves();
    }
    public static User from(UserDTO user, Collection<RoleEnum> roles) {

        return User.builder()
                .id(user.getId())
//                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
//                .birthdate(user.getBirthdate())
                .reserves(user.getReserves())
                .roles(roles)
                .build();
    }
    public static User from(PodUserInfoVo userInfo, Collection<RoleEnum> roles){
        return User.builder()
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .ssoId(userInfo.getSsoId())
//                .phoneNumber(userInfo.getUsername())
//                .birthdate(LocalDate.parse(userInfo.getLegalInfo()))
                .roles(roles)
                .build();
    }
}
