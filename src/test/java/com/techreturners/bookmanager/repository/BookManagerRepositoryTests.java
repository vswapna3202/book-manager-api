package com.techreturners.bookmanager.repository;

import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.model.Genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookManagerRepositoryTests {

    @Autowired
    private BookManagerRepository bookManagerRepository;

    @Test
    public void testFindAllBooksReturnsBooks() {

        Book book = new Book(1L, "Book One", "This is the description for Book One", "Person One", Genre.Education);
        bookManagerRepository.save(book);

        Iterable<Book> books = bookManagerRepository.findAll();
        assertThat(books).hasSize(1);

    }

    @Test
    public void testCreatesAndFindBookByIdReturnsBook() {

        Book book = new Book(1L, "Book Two", "This is the description for Book Two", "Person Two", Genre.Fantasy);
        bookManagerRepository.save(book);

        var bookById = bookManagerRepository.findById(book.getId());
        assertThat(bookById).isNotNull();

    }

    @Test
    public void testDeleteBookByIdReturnsBoolean(){
        //Insert new book
        Book book = new Book(1L, "Book One", "This is the description for Book 1","Person One", Genre.Fiction);
        bookManagerRepository.save(book);
        //Fetch book on id and check if it is not null and exists
        Optional<Book> bookById = bookManagerRepository.findById(book.getId());
        assertThat(bookById).isNotNull();

        //delete the inserted book
        bookManagerRepository.deleteById(book.getId());
        //Fetch book on id and check if it is empty and does not exist
        Optional<Book> bookByIdAfterDeletion =
                bookManagerRepository.findById(book.getId());
        assertThat(bookByIdAfterDeletion).isEmpty();
    }

}
