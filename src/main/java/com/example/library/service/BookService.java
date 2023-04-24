package com.example.library.service;

import com.example.library.entity.BookEntity;

import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.Book;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.E;
import static java.lang.Math.min;


@Service
@RequiredArgsConstructor
public class BookService{
    final BookRepository repository;
    public List<Book> getBooks(int page, int size) {

        return this.repository.findAll(PageRequest.of(page, size)).getContent()
                .stream().map(bookEntity -> Book.from(bookEntity)).toList();
    }

    public Book getBook(long id) {
        return Book.from(this.repository.findById(id).orElseThrow(()-> new BaseException("There is no book with this id.", ExceptionsEnum.NOT_EXIST)));
    }

    public Book addBook(Book book) {
        book.setReserved(false);
        return Book.from(this.repository.save(BookEntity.from((book))));
    }
}
