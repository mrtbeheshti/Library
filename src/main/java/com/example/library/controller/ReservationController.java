package com.example.library.controller;

import com.example.library.object.ReservationDTO;
import com.example.library.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reserves")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;


    @PostMapping()
    public ReservationDTO reserve(
            @RequestParam(value = "user_id") long userId,
            @RequestParam(value = "book_id") long bookId) {
        return this.service.reserve(bookId,userId);
    }

    @DeleteMapping("{bookId}")
    public ReservationDTO endReservation(@PathVariable long bookId) {
        return this.service.endReservation(bookId);
    }
}
