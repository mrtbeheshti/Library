package com.example.library.object;

import com.example.library.entity.BookEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private List<String> authors;
    private String language;
    private LocalDate publishDate;
    private String publisher;
    private List<String> categories;
    private float rate;
    private boolean isReserved;

    public static Book from(BookEntity entity){
        return Book.builder()
                .authors(entity.getAuthors())
                .title(entity.getTitle())
                .language(entity.getLanguage())
                .publishDate(entity.getPublishDate())
                .publisher(entity.getPublisher())
                .categories(entity.getCategories())
                .rate(entity.getRate())
                .isReserved(entity.isReserved())
                .build();
    }
}
