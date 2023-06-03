package com.example.library.entity;

import com.example.library.enums.RoleEnum;
import com.example.library.object.ReservationDTO;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Setter
@Getter
@Entity(name = "lib_reservation")
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity{

    @ManyToOne (fetch = FetchType.EAGER,targetEntity = User.class)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToOne(targetEntity = Book.class)
    @JoinColumn(name = "book_id",unique = true,nullable = false)
    private Book book;

    @Column(name = "reserve_date",nullable = false)
    @CreationTimestamp
    private LocalDateTime reserveDate;

    public void map(ReservationDTO reserve){
        this.user.map(reserve.getUser());
        this.book.map(reserve.getBook());
        this.reserveDate=reserve.getReserveDate();
    }

    public static Reservation from(ReservationDTO reserve){
        return Reservation
                .builder()
                .id(reserve.getId())
                .user(User.from(reserve.getUser(), List.of(RoleEnum.ROLE_USER)))
                .book(Book.from(reserve.getBook()))
                .reserveDate(reserve.getReserveDate())
                .build();
    }

}
