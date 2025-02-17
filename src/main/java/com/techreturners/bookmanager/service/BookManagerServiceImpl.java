package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.exception.BookAlreadyExistsException;
import com.techreturners.bookmanager.exception.BookNotFoundException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.repository.BookManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookManagerServiceImpl implements BookManagerService {

    @Autowired
    BookManagerRepository bookManagerRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookManagerRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book insertBook(Book book) {
        String title = book.getTitle();
        if (title != null) {
            Optional<Book> bookOptional = bookManagerRepository.findByTitle(title);
            if (bookOptional.isPresent()) {
                throw new BookAlreadyExistsException("Book with title: " +
                        book.getTitle() +
                        " already exists and cannot be inserted again");
            }
        }
        return bookManagerRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {

        if (id != null){
            Optional<Book> bookOptional = bookManagerRepository.findById(id);
            if (bookOptional.isPresent()){
                return bookOptional.get();
            }
        }
        throw new BookNotFoundException("Book with book id: "+
                id+" is not found");
    }

    //User Story 4 - Update Book By Id Solution
    @Override
    public void updateBookById(Long id, Book book) {
        Book retrievedBook = bookManagerRepository.findById(id).get();

        retrievedBook.setTitle(book.getTitle());
        retrievedBook.setDescription(book.getDescription());
        retrievedBook.setAuthor(book.getAuthor());
        retrievedBook.setGenre(book.getGenre());

        bookManagerRepository.save(retrievedBook);
    }

    @Override
    public boolean deleteBookById(Long id){
        Optional<Book> bookOptional = bookManagerRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookManagerRepository.deleteById(id);
            return true;
        }
        throw new BookNotFoundException("Book with book Id: "+id+" is not found"+
        " for delete");
    }

}
