package co.simplon.livravenir.services;

import java.util.Collection;

import javax.validation.Valid;

import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItemList;
import co.simplon.livravenir.dtos.BookUpdate;

public interface BookService {

    void createBook(@Valid BookCreate inputs);

    Collection<BookItemList> getAllBooks();

    void updateBook(Long id, BookUpdate inputs);

    BookDetail getdBookDetail(Long id);

    void deleteBook(Long id);

}
