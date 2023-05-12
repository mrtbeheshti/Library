package com.example.library.object;

import com.example.library.entity.Book;
import com.example.library.enums.CategoryEnum;
import com.example.library.enums.LanguageEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Book", description = "Book DTO uses in controller")
public class BookDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "Book id",access = "READ_ONLY")
    private long id;

    @NotNull(message = "title is required")
    @JsonProperty(value = "title")
    @ApiModelProperty(value = "title of Book")
    private String title;

    @NotNull(message = "authors is required")
    @JsonProperty(value = "authors")
    @ApiModelProperty(value = "authors of book")
    private List<String> authors;

    @NotNull(message = "language is required")
    @JsonProperty(value = "language")
    @ApiModelProperty(value = "Language of book")
    private LanguageEnum language;

    @JsonProperty(value = "publish_date")
    @ApiModelProperty(value = "publish date of book")
    private LocalDate publishDate;

    @JsonProperty(value = "publisher")
    @ApiModelProperty("Publisher of book")
    private String publisher;

    @JsonProperty(value = "categories")
    @ApiModelProperty("Categories of book")
    private List<CategoryEnum> categories;

    @JsonProperty(value = "rate",defaultValue = "0.0",access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty("Book rating")
    private float rate;

    @JsonIgnore
    @JsonProperty(defaultValue = "false")
    @ApiModelProperty(value = "Is book reserved?")
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
