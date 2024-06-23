package co.simplon.livravenir.repositories;

import java.util.List;
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

    List<BookItem> findAllBooksByOrderByAddedAtDesc();

    BookForUpdate findProjectedById(Long id);

    List<BookItemAdmin> findAllByOrderByAddedAtDesc();

    BookDetail findProjectedDetailById(Long id);

    boolean existsByIsbn(String string);

    Set<BookDetail> findAllForUpdateProjectedBy();

}