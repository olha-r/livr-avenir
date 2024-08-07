package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.livravenir.entities.BookAuthor;

@Repository
public interface BookAuthorsRepository
	extends JpaRepository<BookAuthor, Long> {

    @Modifying
    @Query("DELETE FROM BookAuthor ba WHERE ba.book.id = :bookId")
    void deleteBookAuthorsByBookId(
	    @Param("bookId") Long id);

    @Modifying
    @Query("DELETE FROM BookAuthor ba WHERE ba.author.id = :authorId")
    void deleteBookAuthorsByAuthorId(
	    @Param("authorId") Long id);

}
