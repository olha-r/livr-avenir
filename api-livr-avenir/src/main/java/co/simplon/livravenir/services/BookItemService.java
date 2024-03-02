package co.simplon.livravenir.services;

import co.simplon.livravenir.dtos.BookItemCreate;
import jakarta.validation.Valid;

public interface BookItemService {

    void createItem(@Valid BookItemCreate inputs);

}
