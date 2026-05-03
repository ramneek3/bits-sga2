package com.example.demo.repository;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFindAllBooksWithAuthors() {
        Author author = new Author("Jane Doe", "A great author");
        authorRepository.save(author);

        Book book = new Book("Spring Boot Basics", "123456", 25.00, author);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAllBooksWithAuthors();
        
        assertFalse(books.isEmpty());
        assertEquals("Spring Boot Basics", books.get(0).getTitle());
        assertEquals("Jane Doe", books.get(0).getAuthor().getName());
    }
}
