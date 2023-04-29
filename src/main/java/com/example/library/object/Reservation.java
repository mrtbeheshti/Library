package com.example.library.object;

import com.example.library.entity.ReservationEntity;
import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @JsonProperty(value = "user")
    private User user;
    @JsonProperty(value = "book")
    private Book book;
    @JsonProperty(value = "reserve_date")
    private LocalDateTime reserveDate;
    @JsonProperty(value = "return_date")
    private LocalDateTime returnDate;


    public static Reservation from(ReservationEntity reserve) {
        return Reservation.builder()
                .id(reserve.getId())
                .user(User.from(reserve.getUser()))
                .book(Book.from(reserve.getBook()))
                .reserveDate(reserve.getReserveDate())
                .returnDate(reserve.getReturnDate())
                .build();
    }
}
