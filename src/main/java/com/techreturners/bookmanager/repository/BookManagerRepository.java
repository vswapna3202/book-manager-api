package com.techreturners.bookmanager.repository;

import com.techreturners.bookmanager.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookManagerRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

}
