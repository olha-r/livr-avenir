package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.BookAuthors;

public interface BookAuthorsRepository
	extends JpaRepository<BookAuthors, Long> {

}
