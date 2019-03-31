package com.baeldung.boot.bootstrapsimpleapplication.web.repository;

import com.baeldung.boot.bootstrapsimpleapplication.web.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
