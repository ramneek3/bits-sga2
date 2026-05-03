package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;

import java.util.List;

public interface LibraryService {
    
    // Author operations
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author saveAuthor(Author author);
    
    // Book operations
    List<Book> getAllBooksWithAuthors();
    Book getBookById(Long id);
    Book saveBook(Book book);
    
    // Initialization
    void populateSampleData();
}
