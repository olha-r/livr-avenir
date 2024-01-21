package co.simplon.livravenir.services;

import java.util.Set;

import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookForUpdate;
import co.simplon.livravenir.dtos.BookItemAdminView;
import co.simplon.livravenir.dtos.BookItemView;
import co.simplon.livravenir.dtos.BookUpdate;
import co.simplon.livravenir.dtos.BookView;
import jakarta.validation.Valid;

public interface BookService {

    void createBook(@Valid BookCreate inputs);

    Set<BookItemView> getAllBooks();

    Set<BookItemAdminView> getAllBooksForAdmin();

    void deleteBook(Long id);

    void updateBook(@Valid Long id, BookUpdate inputs);

    BookView getdBookDetail(Long id);

    BookForUpdate forUpdate(Long id);

    boolean isbnExist(String isbn);

}
