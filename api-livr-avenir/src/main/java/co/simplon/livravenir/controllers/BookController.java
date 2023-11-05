package co.simplon.livravenir.controllers;

import java.util.Set;

import javax.validation.Valid;

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
import co.simplon.livravenir.dtos.BookItemAdminView;
import co.simplon.livravenir.dtos.BookItemView;
import co.simplon.livravenir.dtos.BookUpdate;
import co.simplon.livravenir.dtos.BookView;
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
	    @ModelAttribute @Valid BookCreate inputs) {
	bookService.createBook(inputs);
    }

    @GetMapping
    public Set<BookItemView> getAllBooks() {
	return bookService.getAllBooks();
    }

    @GetMapping("/admin")
    public Set<BookItemAdminView> getAllBooksForAdmin() {
	return bookService.getAllBooksForAdmin();
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

    @GetMapping("/{id}/detail")
    public BookView getdBookDetail(
	    @PathVariable("id") Long id) {
	return bookService.getdBookDetail(id);
    }
}
