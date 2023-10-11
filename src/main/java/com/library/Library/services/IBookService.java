package com.library.Library.services;

import com.library.Library.persistence.models.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    Optional<Book> findById(Long id);

    Book save(Book project);

    void add(Book book);

    List<Book> getAllBooks();

    void deleteBook(Long id);
}
