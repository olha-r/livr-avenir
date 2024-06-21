package co.simplon.livravenir.controllers;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.BookItemCreate;
import co.simplon.livravenir.dtos.BookItemUpdate;
import co.simplon.livravenir.dtos.ItemListForBook;
import co.simplon.livravenir.dtos.ItemListForUser;
import co.simplon.livravenir.services.BookItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/book-items")
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

    @GetMapping("/{bookId}")
    public Set<ItemListForBook> getAllItemsByBook(
	    @PathVariable("bookId") Long bookId) {
	return bookItemService.getAllItemsByBook(bookId);
    }

    @GetMapping("/user")
    public Set<ItemListForUser> getItemsByUserId() {
	return bookItemService.getItemsByUserId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookItem(
	    @PathVariable("id") Long id) {
	bookItemService.deleteBookItem(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBookItem(@PathVariable("id") Long id,
	    @Valid @RequestBody BookItemUpdate inputs) {
	bookItemService.updateBookItem(id, inputs);
    }
}
