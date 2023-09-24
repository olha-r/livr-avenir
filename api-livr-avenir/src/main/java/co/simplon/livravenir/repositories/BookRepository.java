package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.Book;

public interface BookRepository
	extends JpaRepository<Book, Long> {

}