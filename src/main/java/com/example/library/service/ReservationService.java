package com.example.library.service;

import com.example.library.entity.BookEntity;
import com.example.library.entity.ReservationEntity;
import com.example.library.entity.UserEntity;
import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.Book;
import com.example.library.object.Reservation;
import com.example.library.object.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.ReservationRepository;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.library.exception.ExceptionsEnum.NOT_EXIST;


@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public Reservation reserve(long _bookId, long _userId) {
        Book book = Book.from(this.bookRepository.findById(_bookId).orElseThrow(
                () -> new BaseException("There is no book with this id.", NOT_EXIST)
        ));
        User user = User.from(this.userRepository.findById(_userId).orElseThrow(
                () -> new BaseException("there is no user by this id.", NOT_EXIST)
        ));

        Reservation reservation = Reservation.builder().book(book).user(user).build();
        reservation.reserveBook();

        this.bookRepository.save(BookEntity.from(book));
        this.userRepository.save(UserEntity.from(user));
        return Reservation.from(reservationRepository.save(ReservationEntity.from(reservation)));
    }
    public Reservation endReservation(long _bookId) {
        Book book =  Book.from(this.bookRepository.findById(_bookId).orElseThrow(() -> new BaseException("There is no book with this id.", NOT_EXIST)
        ));
        Reservation reservation = Reservation.from(this.reservationRepository.findByBookAndReturnDate(BookEntity.from(book),null)
                .orElseThrow(() -> new BaseException("this book doesn't reserved now.",NOT_EXIST)));
        reservation.returnBook();
        this.bookRepository.save(BookEntity.from(reservation.getBook()));
        this.userRepository.save(UserEntity.from(reservation.getUser()));
        return Reservation.from(this.reservationRepository.save(ReservationEntity.from(reservation)));
    }
}
