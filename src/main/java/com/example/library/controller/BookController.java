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
    public List<Book> getBook(@PathVariable long id) {
        return books.stream().filter(book -> book.getId() == id).toList();
    }

    @PostMapping("")
    public String addBook(@RequestBody Book book) {
        book.setReserved(false);
        if (books.stream().anyMatch(b -> book.getId() == b.getId())) {
            return String.format("Book '%s' already exist.",book.getTitle());
        }
        books.add(book);
        return String.format("Book '%s' added successfully.",book.getTitle());
    }
}
