package co.simplon.livravenir.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.BookCreate;
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

}
