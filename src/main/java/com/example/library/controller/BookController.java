package com.example.library.controller;

import com.example.library.object.Book;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/books")
public class BookController {

    final BookService bookService;

    public BookController() {
        this.bookService = new BookService();
    }

    @GetMapping()
    public List<Book> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return  this.bookService.getBooks(page,size);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return this.bookService.getBook(id);
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book) {
        return this.bookService.addBook(book);
    }
}
