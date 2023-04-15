package com.example.library.controller;

import com.example.library.object.Book;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/books")
public class BookController {

    final BookService service;

    public BookController() {
        this.service = new BookService();
    }

    @GetMapping()
    public List<Book> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return  this.service.getBooks(page,size);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return this.service.getBook(id);
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book) {
        return this.service.addBook(book);
    }
}
