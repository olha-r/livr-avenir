package co.simplon.livravenir.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItem;
import co.simplon.livravenir.dtos.BookItemAdmin;
import co.simplon.livravenir.entities.Book;

public interface BookRepository
	extends JpaRepository<Book, Long> {

    Set<BookItem> findAllBooksProjectedBy();

    BookDetail findProjectedById(Long id);

    Set<BookItemAdmin> findAllProjectedBy();

}