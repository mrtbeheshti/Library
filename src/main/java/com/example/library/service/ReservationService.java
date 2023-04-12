package com.example.library.service;

import com.example.library.object.Book;
import com.example.library.object.Reservation;
import com.example.library.object.User;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;

import static com.example.library.object.Library.*;

@Service
public class ReservationService {

    public Reservation reserve(long _bookId, long _userId) throws NoPermissionException {
        Book book = books.stream().filter(book1 -> book1.getId() == _bookId).findFirst().orElseThrow();
        User user = users.stream().filter(user1 -> user1.getId() == _userId).findFirst().orElseThrow();
        Reservation reservation = new Reservation(user, book, null, null);
        reservation.reserveBook();
        reservations.add(reservation);
        return reservation;
    }
    public Reservation endReservation(long _bookId){
        Reservation reservation = reservations.stream().filter(
                reservation1 -> (reservation1.getBook().getId() == _bookId
                        && reservation1.getReturnDate() == null)
        ).toList().get(0);
        reservation.returnBook();
        return reservation;
    }
}
