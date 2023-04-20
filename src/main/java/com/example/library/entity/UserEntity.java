package com.example.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private LocalDate birthdate;

    @OneToMany( targetEntity = ReservationEntity.class,
                fetch = FetchType.LAZY,
                mappedBy = "user")
    private Set<ReservationEntity> reserves;

}
