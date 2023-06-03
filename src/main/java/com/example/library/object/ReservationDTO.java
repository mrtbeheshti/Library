package com.example.library.object;

import com.example.library.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "Reservation", description = "Reservation DTO uses in controller")
public class ReservationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotNull(message = "user is required")
    @JsonProperty(value = "user")
    @ApiModelProperty("the user who reserved the book.")
    private UserDTO user;

    @NotNull(message = "book is required")
    @JsonProperty(value = "book")
    @ApiModelProperty("the book was reserved.")
    private BookDTO book;

    @JsonProperty(value = "reserve_date")
    @CreationTimestamp
    @ApiModelProperty("date and time of reserve.format: YYYY-MM-DDThh:mm:ss, auto-set")
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
