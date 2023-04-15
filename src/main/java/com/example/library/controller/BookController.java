package com.example.library.controller;

import com.example.library.object.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.library.object.Library.books;
import static java.lang.Math.min;

@RestController("/books")
public class BookController {
//    public static List<Book> books = new ArrayList<>();

    @GetMapping()
    public List<Book> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return books.subList(page * size, min((page + 1) * size, books.size()));
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
