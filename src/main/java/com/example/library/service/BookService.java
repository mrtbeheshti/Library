package com.example.library.service;

import com.example.library.object.Book;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.library.object.Library.books;
import static java.lang.Math.min;


@Service
public class BookService {

    public List<Book> getBooks(int page,int size) {
        return books.subList(page * size, min((page + 1) * size, books.size()));
    }

    public Book getBook(long id) {
        return books.stream().filter(book -> book.getId() == id).toList().get(0);
    }

    public Book addBook(Book book) {
        book.setReserved(false);
        if (books.stream().anyMatch(b -> book.getId() == b.getId())) {
            throw  new RuntimeException(String.format("Book %s already exist.",book.getTitle()));
        }
        books.add(book);
        return book;
    }
}
