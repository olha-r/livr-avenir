package co.simplon.livravenir.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.AuthorCreate;
import co.simplon.livravenir.dtos.AuthorDetail;
import co.simplon.livravenir.services.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
	this.authorService = authorService;
    }

    @GetMapping
    public Collection<AuthorDetail> getAllAuthors() {
	return authorService.getAllAuthors();

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void createAuthor(
	    @Valid @RequestBody AuthorCreate inputs) {
	authorService.createAuthor(inputs);
    }

}
