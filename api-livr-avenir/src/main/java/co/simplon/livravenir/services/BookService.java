package co.simplon.livravenir.services;

import javax.validation.Valid;

import co.simplon.livravenir.dtos.BookCreate;

public interface BookService {

    void createBook(@Valid BookCreate inputs);

}
