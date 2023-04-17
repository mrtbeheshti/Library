package com.example.library.service;

import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.Book;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.library.object.Library.books;
import static java.lang.Math.E;
import static java.lang.Math.min;


@Service
public class BookService {

    public List<Book> getBooks(int page,int size) {
        return books.subList(page * size, min((page + 1) * size, books.size()));
    }

    public Book getBook(long id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElseThrow(()-> new BaseException("There is no book with this id.", ExceptionsEnum.NOT_EXIST));
    }

    public Book addBook(Book book) {
        book.setReserved(false);
        if (books.stream().anyMatch(b -> book.getId() == b.getId())) {
            throw new BaseException(String.format("Book %s already exist.",book.getTitle()),ExceptionsEnum.ALREADY_EXIST);
        }
        books.add(book);
        return book;
    }
}
