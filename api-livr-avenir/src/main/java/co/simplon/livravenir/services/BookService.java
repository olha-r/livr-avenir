package co.simplon.livravenir.services;

import java.util.Collection;

import javax.validation.Valid;

import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItem;

public interface BookService {

    void create(@Valid BookCreate inputs);

    BookDetail detail(Long id);

    Collection<BookItem> getAll();

}
