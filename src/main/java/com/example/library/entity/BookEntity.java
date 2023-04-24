package com.example.library.entity;

import com.example.library.object.Book;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity extends BaseEntity{

    @Column
    private String title;

    @Column()
    @ElementCollection
    private List<String> authors;

    @Column
    private String language;

    @Column
    private LocalDate publishDate;

    @Column
    private String publisher;

    @Column
    @ElementCollection
    private List<String> categories;

    @Column
    private float rate;

    @Column
    private boolean isReserved;

    public static BookEntity from(Book book){
        return BookEntity.builder()
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
