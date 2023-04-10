package com.example.library.object;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private User user;
    private Book book;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
}
