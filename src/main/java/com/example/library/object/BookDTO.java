package com.example.library.object;

import com.example.library.entity.Book;
import com.example.library.enums.CategoryEnum;
import com.example.library.enums.LanguageEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "title is required")
    @JsonProperty(value = "title")
    private String title;

    @NotNull(message = "authors is required")
    @JsonProperty(value = "authors")
    private List<String> authors;

    @NotNull(message = "language is required")
    @JsonProperty(value = "language")
    private LanguageEnum language;

    @JsonProperty(value = "publish_date")
    private LocalDate publishDate;

    @JsonProperty(value = "publisher")
    private String publisher;

    @JsonProperty(value = "categories")
    private List<CategoryEnum> categories;

    @JsonProperty(value = "rate",defaultValue = "0.0",access = JsonProperty.Access.READ_ONLY)
    private float rate;

    @JsonIgnore
    @JsonProperty(defaultValue = "false")
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
