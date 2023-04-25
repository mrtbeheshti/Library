package com.example.library.repository;

import com.example.library.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
    Page<BookEntity> findAll(Pageable pageable);
    Optional<BookEntity> findById(long id);
    @SuppressWarnings("unchecked")
    BookEntity save(BookEntity bookEntity);
}
