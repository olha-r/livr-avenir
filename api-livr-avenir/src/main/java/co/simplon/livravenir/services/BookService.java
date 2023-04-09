package co.simplon.livravenir.services;

import java.util.Collection;

import javax.validation.Valid;

import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItem;
import co.simplon.livravenir.dtos.BookUpdate;

public interface BookService {

    void create(@Valid BookCreate inputs);

    BookDetail detail(Long id);

    void update(Long id, BookUpdate inputs);

    void delete(Long id);

    Collection<BookItem> getTop4LastAdded();

}
