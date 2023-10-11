package com.library.Library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.library.Library.persistence.models.Book;
import com.library.Library.services.Impl.BookServiceImpl;

import java.util.ArrayList;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping
    public ArrayList<Book> getAll(Model model){
        System.out.println("Entra");
        ArrayList<Book> books = (ArrayList<Book>) this.bookService.getAllBooks();
        return books;
    }

    @PostMapping
    public String createBook( @ModelAttribute Book newBook){
        this.bookService.save(newBook);
        return "Book created";
    }

    @GetMapping(path = "/{id}")
    public Book getBookById(Model model, @PathVariable("id") Long id){
        if(this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            model.addAttribute(book);
            return book;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PutMapping(path = "/{id}")
    public String updateBookById(Model model, @PathVariable("id") Long id){
        if(this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            model.addAttribute(book);
            return "Book modified";
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @DeleteMapping(path = "/{id}po")
    public String deleteBookById(@PathVariable("id") Long id){
        if(this.bookService.findById(id).isPresent()) {
            this.bookService.deleteBook(id);
            return "Book deleted";
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }
}