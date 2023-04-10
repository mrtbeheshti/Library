package com.example.library.object;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String title;
    private List<String> authors;
    private String language;
    private LocalDate publishDate;
    private String publisher;
    private List<String> categories;
    private float rate;
    private boolean isReserved;
}
