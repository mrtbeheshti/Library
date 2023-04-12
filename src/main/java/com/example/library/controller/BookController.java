package com.example.library.controller;

import com.example.library.object.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return  bookService.getBooks(page,size);
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id) {
        return  bookService.getBook(id);
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}
