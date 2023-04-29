package com.example.library.repository;

import com.example.library.entity.BookEntity;
import com.example.library.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity,Long> {
    Optional<ReservationEntity> findByBookAndReturnDate(BookEntity book, LocalDateTime returnDate);
}
