package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.BookAuthor;

public interface BookAuthorsRepository
	extends JpaRepository<BookAuthor, Long> {

}
