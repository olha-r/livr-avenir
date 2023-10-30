package co.simplon.livravenir.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.AuthorDetail;
import co.simplon.livravenir.services.AuthorService;

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

}
