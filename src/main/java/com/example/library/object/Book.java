package com.example.library.object;

import com.example.library.entity.BookEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String title;
    private List<String> authors;
    private String language;
    private LocalDate publishDate;
    private String publisher;
    private List<String> categories;
    private float rate;
    private boolean isReserved;

    public static Book from(BookEntity book){
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
