package com.example.library.controller;

import com.example.library.object.Book;
import com.example.library.object.Reservation;
import com.example.library.object.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;

import static com.example.library.object.Library.*;

@RestController
public class ReservationController {

    @PostMapping("/reserve")
    public String reserve(@RequestParam(value = "user_id") long userId, @RequestParam(value = "book_id") long bookId) throws NoPermissionException {
        Book book = books.stream().filter(book1 -> book1.getId() == bookId).findFirst().orElseThrow();
        User user = users.stream().filter(user1 -> user1.getId() == userId).findFirst().orElseThrow();
        Reservation newReservation = new Reservation(user, book, null, null);
        reservations.add(newReservation);
        return newReservation.reserveBook();
    }

    @PutMapping("/return")
    public String endReservation(@RequestParam(value = "book_id") long bookId) {
        Reservation reservation = reservations.stream().filter(
                reservation1 -> (reservation1.getBook().getId() == bookId
                        && reservation1.getReturnDate() == null)
        ).toList().get(0);
        return reservation.returnBook();
    }
}
