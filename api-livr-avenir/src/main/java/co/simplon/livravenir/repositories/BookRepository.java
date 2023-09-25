package co.simplon.livravenir.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItemList;
import co.simplon.livravenir.entities.Book;

public interface BookRepository
	extends JpaRepository<Book, Long> {

    Collection<BookItemList> findAllBooksProjectedBy();

    BookDetail findBookProjectedById(Long id);

}