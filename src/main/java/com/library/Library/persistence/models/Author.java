package com.library.Library.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Author {

    private long id;
    private String name;
    private String nationality;
    private ArrayList<Book> books;

    public Author (Author author){
        this(author.getId(), author.getName(), author.getNationality(), author.getBooks());
    }

    @Override
    public String toString(){
        return ("id: " + this.id + ", name: " + this.name + ", address: " + this.nationality + ", books: "+ this.books.size());
    }
}
