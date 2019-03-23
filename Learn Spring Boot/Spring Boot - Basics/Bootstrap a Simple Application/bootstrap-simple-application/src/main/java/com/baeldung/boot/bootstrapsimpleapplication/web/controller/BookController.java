package com.baeldung.boot.bootstrapsimpleapplication.web.controller;

import com.baeldung.boot.bootstrapsimpleapplication.web.exception.BookIdMismatchException;
import com.baeldung.boot.bootstrapsimpleapplication.web.exception.BookNotFoundException;
import com.baeldung.boot.bootstrapsimpleapplication.web.model.Book;
import com.baeldung.boot.bootstrapsimpleapplication.web.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List<Book> findByTitle(@PathVariable(name = "bookTitle") String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable(name = "id") Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        bookRepository.delete(findOne(id));
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id){
        if(book.getId() != id) {
            throw new BookIdMismatchException();
        }
        findOne(id);
        return bookRepository.save(book);
    }
}
