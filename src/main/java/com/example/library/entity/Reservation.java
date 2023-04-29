package com.example.library.entity;

import com.example.library.object.ReservationDTO;
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
public class Reservation extends BaseEntity{
    @Transient
    final private int MaxReserves = 3;

    @ManyToOne (fetch = FetchType.EAGER,targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(targetEntity = Book.class)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "reserve_date")
    private LocalDateTime reserveDate;


    public static Reservation from(ReservationDTO reserve){
        return Reservation
                .builder()
                .id(reserve.getId())
                .user(User.from(reserve.getUser()))
                .book(Book.from(reserve.getBook()))
                .reserveDate(reserve.getReserveDate())
                .build();
    }

}
