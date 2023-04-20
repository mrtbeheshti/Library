package com.example.library.entity;

import com.example.library.object.Book;
import com.example.library.object.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity extends BaseEntity{

    @Transient
    final private int MaxReserves = 3;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(targetEntity = BookEntity.class)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Column(name = "reserve")
    private LocalDateTime reserveDate;

    @Column(name = "return")
    private LocalDateTime returnDate;


}
