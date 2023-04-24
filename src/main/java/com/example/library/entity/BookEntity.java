package com.example.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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


}
