package com.library.Library.persistence.repositories;

import java.util.List;
import  java.util.Optional;
import com.library.Library.persistence.models.Book;

public interface IBookRepository {

    Optional<Book> findById(Long id);

    Book save(Book project);

    void add(Book book);

    List<Book> getAllBooks();

    void deleteBook(Long id);

}
