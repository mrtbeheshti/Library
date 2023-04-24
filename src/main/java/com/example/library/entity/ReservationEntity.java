package com.example.library.entity;

import com.example.library.object.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
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

    public static ReservationEntity from(Reservation reserve){
        return ReservationEntity
                .builder()
                .user(UserEntity.from(reserve.getUser()))
                .book(BookEntity.from(reserve.getBook()))
                .reserveDate(reserve.getReserveDate())
                .returnDate(reserve.getReturnDate())
                .build();
    }

}
