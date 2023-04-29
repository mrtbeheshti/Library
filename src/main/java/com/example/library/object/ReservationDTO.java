package com.example.library.object;

import com.example.library.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @JsonProperty(value = "user")
    private UserDTO user;
    @JsonProperty(value = "book")
    private BookDTO book;
    @JsonProperty(value = "reserve_date")
    private LocalDateTime reserveDate;


    public static ReservationDTO from(Reservation reserve) {
        return ReservationDTO.builder()
                .id(reserve.getId())
                .user(UserDTO.from(reserve.getUser()))
                .book(BookDTO.from(reserve.getBook()))
                .reserveDate(reserve.getReserveDate())
                .build();
    }
}
