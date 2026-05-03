package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listEntities(Model model) {
        model.addAttribute("books", libraryService.getAllBooksWithAuthors());
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book, Model model) {
        try {
            if (book.getAuthor() == null || book.getAuthor().getId() == null) {
                throw new IllegalArgumentException("Author must be selected");
            }
            Author author = libraryService.getAuthorById(book.getAuthor().getId());
            book.setAuthor(author);
            libraryService.saveBook(book);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Error saving book: " + e.getMessage());
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = libraryService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book bookDetails, Model model) {
        try {
            Book existingBook = libraryService.getBookById(id);
            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setIsbn(bookDetails.getIsbn());
            existingBook.setPrice(bookDetails.getPrice());
            
            if (bookDetails.getAuthor() != null && bookDetails.getAuthor().getId() != null) {
                Author author = libraryService.getAuthorById(bookDetails.getAuthor().getId());
                existingBook.setAuthor(author);
            }
            
            libraryService.saveBook(existingBook);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating book: " + e.getMessage());
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "update";
        }
    }
}
