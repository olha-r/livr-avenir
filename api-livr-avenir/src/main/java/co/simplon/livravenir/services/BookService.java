package co.simplon.livravenir.services;

import java.util.Collection;

import javax.validation.Valid;

import co.simplon.livravenir.dtos.BookAuthorView;
import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookItemList;
import co.simplon.livravenir.dtos.BookUpdate;

public interface BookService {

    void createBook(@Valid BookCreate inputs);

    Collection<BookItemList> getAllBooks();

    void deleteBook(Long id);

    void updateBook(Long id, BookUpdate inputs);

    BookAuthorView getdBookDetail(Long id);

}
