package com.example.library.entity;

import com.example.library.object.BookDTO;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Setter
@Getter
@Entity(name = "lib_book")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity{

    @Column(name = "title",unique = true,nullable = false)
    private String title;

    @Builder.Default
    @Column(name = "authors",nullable = false)
    @ElementCollection
    private List<String> authors= new ArrayList<>();

    @Column(name = "language",nullable = false)
    private String language;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "categories")
    @ElementCollection
    private List<String> categories;

    @Column(name = "rate")
    private float rate;

    @Column(name = "is_reserved")
    private boolean isReserved;


    public static Book from(BookDTO book){
        return Book.builder()
                .id(book.getId())
                .authors(book.getAuthors())
                .title(book.getTitle())
                .language(book.getLanguage())
                .publishDate(book.getPublishDate())
                .publisher(book.getPublisher())
                .categories(book.getCategories())
                .rate(book.getRate())
                .isReserved(book.isReserved())
                .build();

    }

}
