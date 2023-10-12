package com.library.Library.controllers;

import com.library.Library.persistence.models.Author;
import com.library.Library.services.Impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorServiceImpl authorService;

    @GetMapping
    public ArrayList<Author> getAll(Model model){
        ArrayList<Author> authors = (ArrayList<Author>) this.authorService.getAllAuthors();
        return authors;
    }

    @PostMapping
    public String createAuthor( @RequestBody Author newAuthor){
        this.authorService.save(newAuthor);
        return "Author created";
    }

    @GetMapping(path = "/{id}")
    public Author getAuthorById(@PathVariable("id") Long id){
        if(this.authorService.findById(id).isPresent()) {
            Author author = this.authorService.findById(id).get();
            return author;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PutMapping(path = "/{id}")
    public String updateAuthorById(@RequestBody Author newAuthor, @PathVariable("id") Long id){
        if(this.authorService.findById(id).isPresent()) {
            Author author = this.authorService.findById(id).get();
            author.setName(newAuthor.getName());
            author.setNationality(newAuthor.getNationality());

            this.authorService.save(author);
            return "Author modified";
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @DeleteMapping(path = "/{id}")
    public String deleteAuthorById(@PathVariable("id") Long id){
        if(this.authorService.findById(id).isPresent()) {
            this.authorService.deleteAuthor(id);
            return "Author deleted";
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }
}