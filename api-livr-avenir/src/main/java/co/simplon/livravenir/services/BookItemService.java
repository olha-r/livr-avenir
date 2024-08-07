package co.simplon.livravenir.services;

import java.util.Set;

import co.simplon.livravenir.dtos.BookItemCreate;
import co.simplon.livravenir.dtos.BookItemUpdate;
import co.simplon.livravenir.dtos.ItemListForBook;
import co.simplon.livravenir.dtos.ItemListForUser;
import jakarta.validation.Valid;

public interface BookItemService {

    void createItem(@Valid BookItemCreate inputs);

    Set<ItemListForBook> getAllItemsByBook(Long bookId);

    Set<ItemListForUser> getItemsByUserId();

    void deleteBookItem(Long id);

    void updateBookItem(Long id,
	    @Valid BookItemUpdate inputs);

}
