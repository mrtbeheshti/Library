package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.exception.BaseException;
import com.example.library.enums.ExceptionEnum;
import com.example.library.object.BookDTO;
import com.example.library.pojo.log.ThrowableLogPOJO;
import com.example.library.repository.BookRepository;
import com.example.library.security.SocketPrincipal;
import com.example.library.util.LogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j2
@Service
@RequiredArgsConstructor
public class BookService {
    final BookRepository repository;

    public List<BookDTO> getBooks(int page, int size) {

        return this.repository.findAll(PageRequest.of(page, size)).getContent()
                .stream().map(BookDTO::from).toList();
    }

    public BookDTO getBook(long id) {
        Book book = this.repository.findById(id).orElseThrow(() -> {
            SocketPrincipal socketPrincipal = (SocketPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            BaseException exception = new BaseException("There is no book with this id.", ExceptionEnum.NOT_EXIST);
            ThrowableLogPOJO throwableLog  = ThrowableLogPOJO.builder()
                    .message("NotFound at getBook")
                    .ssoId(String.valueOf(socketPrincipal.getSsoId()))
                    .details(exception)
                    .build();
            LogUtil.info(log,throwableLog);
            return exception;
        });
        return BookDTO.from(book);
    }

    public BookDTO addBook(BookDTO book) {
        if (this.repository.existsByTitle(book.getTitle())) {
            throw new BaseException("This book is already exist.", ExceptionEnum.ALREADY_EXIST);
        }
        Book newBook = this.repository.save(Book.from((book)));
        return BookDTO.from(newBook);
    }

    public BookDTO deleteBook(long id) {
        BookDTO book = this.getBook(id);
        this.repository.delete(Book.from(book));
        return book;
    }

}
