package com.example.library.controller;

//import com.example.library.exception.BaseException;
import com.example.library.object.BookDTO;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping()
    public List<BookDTO> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return  this.service.getBooks(page,size);
    }

    @GetMapping("{id}")
    public BookDTO getBook(@PathVariable long id) {
        return this.service.getBook(id);
    }

    @PostMapping()
    public BookDTO addBook(@RequestBody BookDTO book) {
        return this.service.addBook(book);
    }
}
