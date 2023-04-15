package com.example.library.object;

import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
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

    public void reserveBook() {
        if (this.getBook().isReserved())
            throw new BaseException(String.format("%s is reserved right now.", this.getBook().getTitle()), ExceptionsEnum.IS_RESERVED);
        if (this.getUser().getReserves() > this.getMaxReserves() - 1)
            throw new BaseException(String.format("%s %s has reached maximum reserves", this.getUser().getFirstName(), this.getUser().getLastName()), ExceptionsEnum.MAXIMUM_RESERVES_REACHED);
        this.getUser().setReserves(this.getUser().getReserves() + 1);
        this.getBook().setReserved(true);
        this.setReserveDate(LocalDateTime.now());
    }

    public void returnBook() {
        this.getUser().setReserves(this.getUser().getReserves() - 1);
        this.getBook().setReserved(false);
        this.setReturnDate(LocalDateTime.now());
    }
}
