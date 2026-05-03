package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    private Author author;
    private Book book;

    @BeforeEach
    void setUp() {
        author = new Author("Test Author", "Test Bio");
        author.setId(1L);

        book = new Book("Test Book", "ISBN-TEST", 29.99, author);
        book.setId(1L);
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));
        List<Author> authors = libraryService.getAllAuthors();
        assertEquals(1, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testGetAuthorById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        Author found = libraryService.getAuthorById(1L);
        assertNotNull(found);
        assertEquals("Test Author", found.getName());
    }

    @Test
    void testSaveBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book savedBook = libraryService.saveBook(book);
        assertNotNull(savedBook);
        assertEquals("Test Book", savedBook.getTitle());
    }
}
