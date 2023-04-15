package com.example.library.service;

import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.Book;
import com.example.library.object.Reservation;
import com.example.library.object.User;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;

import static com.example.library.object.Library.*;

@Service
public class ReservationService {

    public Reservation reserve(long _bookId, long _userId){
        Book book = books.stream().filter(book1 -> book1.getId() == _bookId).findFirst().orElseThrow(
                () -> new BaseException("There is no book with this id.", ExceptionsEnum.NOT_EXIST)
        );
        User user = users.stream().filter(user1 -> user1.getId() == _userId).findFirst().orElseThrow(
                ()-> new BaseException("there is no user by this id.",ExceptionsEnum.NOT_EXIST)
        );
        Reservation reservation = new Reservation(user, book, null, null);
        reservation.reserveBook();
        reservations.add(reservation);
        return reservation;
    }
    public Reservation endReservation(long _bookId){
        Reservation reservation = reservations.stream().filter(
                reservation1 -> (reservation1.getBook().getId() == _bookId
                        && reservation1.getReturnDate() == null)
        ).findFirst().orElseThrow(
                ()-> new BaseException("There is no book with this id.",ExceptionsEnum.NOT_EXIST)
        );
        reservation.returnBook();
        return reservation;
    }
}
