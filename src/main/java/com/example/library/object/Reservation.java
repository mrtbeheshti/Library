package com.example.library.object;

import lombok.*;

import javax.naming.NoPermissionException;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    final private int MaxReserves = 3;
    private User user;
    private Book book;
    private LocalDateTime reserveDate;
    private LocalDateTime returnDate;

    public String reserveBook() throws NoPermissionException {
        if (this.getBook().isReserved())
            throw new NoPermissionException(String.format("%s is reserved right now.", this.getBook().getTitle()));
        if (this.getUser().getReserves() > this.getMaxReserves() - 1)
            throw new NoPermissionException(String.format("%s %s has reached maximum reserves", this.getUser().getFirstName(), this.getUser().getLastName()));
        this.getUser().setReserves(this.getUser().getReserves() + 1);
        this.getBook().setReserved(true);
        this.setReserveDate(LocalDateTime.now());
        return String.format("%s has reserved in %s by %s %s.",
                this.getBook().getTitle(), this.getReserveDate().toString(), this.getUser().getFirstName(), this.getUser().getLastName());
    }

    public String returnBook() {
        this.getUser().setReserves(this.getUser().getReserves() - 1);
        this.getBook().setReserved(false);
        this.setReturnDate(LocalDateTime.now());
        return String.format("%s has returned in %s",this.getBook().getTitle(),this.getReturnDate().toString());
    }
}
