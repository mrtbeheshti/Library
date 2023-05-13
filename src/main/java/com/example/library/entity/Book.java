package com.example.library.entity;

import com.example.library.enums.CategoryEnum;
import com.example.library.enums.LanguageEnum;
import com.example.library.object.BookDTO;
import javax.persistence.*;
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "language",nullable = false)
    private LanguageEnum language;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "publisher")
    private String publisher;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "categories")
    @ElementCollection
    private List<CategoryEnum> categories;

    @Column(name = "rate")
    private float rate;

    @Column(name = "is_reserved")
    private boolean isReserved;


    public void map(BookDTO book){
        this.title=book.getTitle();
        this.authors=book.getAuthors();
        this.language=book.getLanguage();
        this.publishDate=book.getPublishDate();
        this.publisher=book.getPublisher();
        this.categories=book.getCategories();
        this.rate=book.getRate();
        this.isReserved=book.isReserved();
    }
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
