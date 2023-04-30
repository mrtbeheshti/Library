package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.exception.BaseException;
import com.example.library.exception.ExceptionsEnum;
import com.example.library.object.BookDTO;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService{
    final BookRepository repository;
    public List<BookDTO> getBooks(int page, int size) {

        return this.repository.findAll(PageRequest.of(page, size)).getContent()
                .stream().map(BookDTO::from).toList();
    }

    public BookDTO getBook(long id) {
        return BookDTO.from(this.repository.findById(id).orElseThrow(()-> new BaseException("There is no book with this id.", ExceptionsEnum.NOT_EXIST)));
    }

    public BookDTO addBook(BookDTO book) {
        book.setReserved(false);
        return BookDTO.from(this.repository.save(Book.from((book))));
    }
}
