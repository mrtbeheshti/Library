package com.example.library.repository;

import com.example.library.entity.Book;
import com.example.library.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> findByBookAndReturnDate(Book book, LocalDateTime returnDate);
}
