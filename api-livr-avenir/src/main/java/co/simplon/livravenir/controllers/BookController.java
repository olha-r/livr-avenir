package co.simplon.livravenir.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItemList;
import co.simplon.livravenir.dtos.BookUpdate;
import co.simplon.livravenir.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
	this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void createBook(
	    @ModelAttribute BookCreate inputs) {
	System.out.println(
		"controller, before service" + inputs);
	bookService.createBook(inputs);
    }

    @GetMapping
    public Collection<BookItemList> getAllBooks() {
	return bookService.getAllBooks();
    }

    @GetMapping("/{id}/detail")
    public BookDetail getdBookDetail(
	    @PathVariable("id") Long id) {
	return bookService.getdBookDetail(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("id") Long id,
	    @ModelAttribute BookUpdate inputs) {
	bookService.updateBook(id, inputs);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Long id) {
	bookService.deleteBook(id);
    }

}
