package co.simplon.livravenir.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.BookItemCreate;
import co.simplon.livravenir.services.BookItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/book_items")
public class BookItemController {

    private final BookItemService bookItemService;

    public BookItemController(
	    BookItemService bookItemService) {
	this.bookItemService = bookItemService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void createBookItem(
	    @RequestBody @Valid BookItemCreate inputs) {
	bookItemService.createItem(inputs);
    }
}
