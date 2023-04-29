package com.example.library.object;

import com.example.library.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class BookDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "authors")
    private List<String> authors;
    @JsonProperty(value = "language")
    private String language;
    @JsonProperty(value = "publish_date")
    private LocalDate publishDate;
    @JsonProperty(value = "publisher")
    private String publisher;
    @JsonProperty(value = "categories")
    private List<String> categories;
    @JsonProperty(value = "rate")
    private float rate;
    @JsonIgnore
    private boolean isReserved;

    public static BookDTO from(Book book){
        return BookDTO.builder()
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
