package com.example.library.controller;

import com.example.library.object.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

@RestController()
public class BookController {
    List<Book> books = new ArrayList<>();

    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return books.subList(page * size, min((page + 1) * size, books.size()));
    }

    @GetMapping("/book/{id}")
    public List<Book> getBook(@PathVariable long id) {
        return books.stream().filter(book -> book.getId() == id).toList();
    }

    @PostMapping("/book")
    public String addBook(@RequestBody Book book) {
        book.setReserved(false);
        if (books.stream().anyMatch(b -> book.getId() == b.getId())) {
            return String.format("Book '%s' already exist.",book.getTitle());
        }
        books.add(book);
        return String.format("Book '%s' added successfully.",book.getTitle());
    }


}
