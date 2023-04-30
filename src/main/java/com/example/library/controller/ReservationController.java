package com.example.library.controller;

import com.example.library.object.ReservationDTO;
import com.example.library.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;


    @PostMapping("/reserve")
    public ReservationDTO reserve(
            @RequestParam(value = "user_id") long userId,
            @RequestParam(value = "book_id") long bookId) {
        return this.service.reserve(bookId,userId);
    }

    @PutMapping("/return")
    public ReservationDTO endReservation(@RequestParam(value = "book_id") long bookId) {
        return this.service.endReservation(bookId);
    }
}
