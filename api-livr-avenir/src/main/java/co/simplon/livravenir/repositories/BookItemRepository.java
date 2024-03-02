package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.BookItem;

public interface BookItemRepository
	extends JpaRepository<BookItem, Long> {

}
