package com.example.library.object;

import com.example.library.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @NotNull(message = "user is required")
    @JsonProperty(value = "user")
    private UserDTO user;

    @NotNull(message = "book is required")
    @JsonProperty(value = "book")
    private BookDTO book;

    @JsonProperty(value = "reserve_date")
    @CreationTimestamp
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
