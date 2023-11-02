package co.simplon.livravenir.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItem;
import co.simplon.livravenir.entities.Book;

public interface BookRepository
	extends JpaRepository<Book, Long> {

    List<BookItem> findAllBooksProjectedBy();

    BookDetail findProjectedById(Long id);

}