package com.example.library.entity;

import com.example.library.object.Reservation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Setter
@Getter
@Entity(name = "lib_reservation")
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity extends BaseEntity{
    @Transient
    final private int MaxReserves = 3;

    @ManyToOne (fetch = FetchType.EAGER,targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(targetEntity = BookEntity.class)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Column(name = "reserve_date")
    private LocalDateTime reserveDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    public static ReservationEntity from(Reservation reserve){
        return ReservationEntity
                .builder()
                .id(reserve.getId())
                .user(UserEntity.from(reserve.getUser()))
                .book(BookEntity.from(reserve.getBook()))
                .reserveDate(reserve.getReserveDate())
                .returnDate(reserve.getReturnDate())
                .build();
    }

}
