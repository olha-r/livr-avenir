package co.simplon.livravenir.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookForUpdate;
import co.simplon.livravenir.dtos.BookItem;
import co.simplon.livravenir.dtos.BookItemAdmin;
import co.simplon.livravenir.entities.Book;

@Repository
public interface BookRepository
	extends JpaRepository<Book, Long> {

    Set<BookItem> findAllBooksProjectedBy();

    BookForUpdate findProjectedById(Long id);

    Set<BookItemAdmin> findAllProjectedBy();

    BookDetail findProjectedDetailById(Long id);

    boolean existsByIsbn(String string);

}