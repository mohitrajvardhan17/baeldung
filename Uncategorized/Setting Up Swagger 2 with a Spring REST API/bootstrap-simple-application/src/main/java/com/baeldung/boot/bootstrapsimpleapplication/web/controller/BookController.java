package com.baeldung.boot.bootstrapsimpleapplication.web.controller;

import com.baeldung.boot.bootstrapsimpleapplication.web.exception.BookIdMismatchException;
import com.baeldung.boot.bootstrapsimpleapplication.web.exception.BookNotFoundException;
import com.baeldung.boot.bootstrapsimpleapplication.web.model.Book;
import com.baeldung.boot.bootstrapsimpleapplication.web.repository.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Api(value = "Book Api", tags = "Book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @ApiOperation(notes = "Operation Id 1", value = "Get all books", nickname = "BookController_findAll", tags = { "BookController" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Book.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }


    @ApiOperation(notes = "Operation Id 2", value = "Get book by title", nickname = "BookController_findByTitle", tags = { "BookController" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Book.class),
            @ApiResponse(code = 400, message = "Invalid Parameters"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/title/{bookTitle}", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
    public List<Book> findByTitle(
            @ApiParam(value = "bookTitle", required = true) @PathVariable(name = "bookTitle", required = true) String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @ApiOperation(notes = "Operation Id 3", value = "Get book by id", nickname = "BookController_findOne", tags = { "BookController" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Book.class),
            @ApiResponse(code = 400, message = "Invalid Parameters"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" } )
    public Book findOne(
            @ApiParam(value = "bookId", required = true) @PathVariable(name = "id", required = true) Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @ApiOperation(notes = "Operation Id 4", value = "Create a new book", nickname = "BookController_create", tags = { "BookController" })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Book.class),
            @ApiResponse(code = 400, message = "Invalid Parameters"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" } )
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(
            @ApiParam(value = "Book", required = true) @Valid @NotNull @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @ApiOperation(notes = "Operation Id 5", value = "Delete a existing book by its id", nickname = "BookController_delete", tags = { "BookController" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Book.class),
            @ApiResponse(code = 400, message = "Invalid Parameters"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/json" }, produces = { "application/json" } )
    public void delete(
            @ApiParam(value = "BookId", required = true) @PathVariable(name = "id") long id) {
        bookRepository.delete(findOne(id));
    }

    @ApiOperation(notes = "Operation Id 6", value = "Modify a existing book by its id", nickname = "BookController_updateBook", tags = { "BookController" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Book.class),
            @ApiResponse(code = 400, message = "Invalid Parameters"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { "application/json" }, produces = { "application/json" } )
    public Book updateBook(
            @ApiParam(value = "Book", required = true) @RequestBody Book book,
            @ApiParam(value = "BookId", required = true) @PathVariable Long id){
        if(book.getId() != id) {
            throw new BookIdMismatchException();
        }
        findOne(id);
        return bookRepository.save(book);
    }
}
