package co.simplon.livravenir.services;

import java.util.Set;

import co.simplon.livravenir.dtos.BookItemCreate;
import co.simplon.livravenir.dtos.ItemListForBook;
import jakarta.validation.Valid;

public interface BookItemService {

    void createItem(@Valid BookItemCreate inputs);

    Set<ItemListForBook> getAllItemsByBook(Long bookId);

}
