package com.example.library.controller;

import com.example.library.object.Reservation;
import com.example.library.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;


    @PostMapping("/reserve")
    public Reservation reserve(
            @RequestParam(value = "user_id") long userId,
            @RequestParam(value = "book_id") long bookId) {
        return this.service.reserve(bookId,userId);
    }

    @PutMapping("/return")
    public Reservation endReservation(@RequestParam(value = "book_id") long bookId) {
        return this.service.endReservation(bookId);
    }
}
