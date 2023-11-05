package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.livravenir.entities.BookAuthor;

public interface BookAuthorsRepository
	extends JpaRepository<BookAuthor, Long> {

    @Modifying
    @Query("DELETE FROM BookAuthor ba WHERE ba.book.id = :bookId")
    void deleteBookAuthorsByBookId(
	    @Param("bookId") Long id);

}
