package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.entity.Reservation;
import com.example.library.entity.User;
import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.BookDTO;
import com.example.library.object.ReservationDTO;
import com.example.library.object.UserDTO;
import com.example.library.repository.BookRepository;
import com.example.library.repository.ReservationRepository;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.library.exception.ExceptionsEnum.NOT_EXIST;


@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    public ReservationDTO reserve(long _bookId, long _userId) {
        BookDTO book = BookDTO.from(this.bookRepository.findById(_bookId).orElseThrow(
                () -> new BaseException("There is no book with this id.", NOT_EXIST)
        ));
        UserDTO user = UserDTO.from(this.userRepository.findById(_userId).orElseThrow(
                () -> new BaseException("there is no user by this id.", NOT_EXIST)
        ));

        ReservationDTO reservation = ReservationDTO.builder().book(book).user(user).build();
        this.reserveBook(reservation);

        this.bookRepository.save(Book.from(book));
        this.userRepository.save(User.from(user));
        return ReservationDTO.from(reservationRepository.save(Reservation.from(reservation)));
    }
    public ReservationDTO endReservation(long _bookId) {
        BookDTO book =  BookDTO.from(this.bookRepository.findById(_bookId).orElseThrow(() -> new BaseException("There is no book with this id.", NOT_EXIST)
        ));
        ReservationDTO reservation = ReservationDTO.from(this.reservationRepository.findByBookAndReturnDate(Book.from(book),null)
                .orElseThrow(() -> new BaseException("this book doesn't reserved now.",NOT_EXIST)));
        this.returnBook(reservation);
        this.bookRepository.save(Book.from(reservation.getBook()));
        this.userRepository.save(User.from(reservation.getUser()));
        return ReservationDTO.from(this.reservationRepository.save(Reservation.from(reservation)));
    }

    public void reserveBook(ReservationDTO reservation) {
        if (reservation.getBook().isReserved())
            throw new BaseException(String.format("%s is reserved right now.", reservation.getBook().getTitle()), ExceptionsEnum.IS_RESERVED);
        int MAX_RESERVES = 3;
        if (reservation.getUser().getReserves() > MAX_RESERVES - 1)
            throw new BaseException(String.format("%s %s has reached maximum reserves", reservation.getUser().getFirstName(), reservation.getUser().getLastName()), ExceptionsEnum.MAXIMUM_RESERVES_REACHED);
        reservation.getUser().setReserves(reservation.getUser().getReserves() + 1);
        reservation.getBook().setReserved(true);
        reservation.setReserveDate(LocalDateTime.now());
    }


    public void returnBook(ReservationDTO reservation) {
        reservation.getUser().setReserves(reservation.getUser().getReserves() - 1);
        reservation.getBook().setReserved(false);
        reservation.setReturnDate(LocalDateTime.now());
    }

}
