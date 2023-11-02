package co.simplon.livravenir.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.livravenir.dtos.AuthorDetail;
import co.simplon.livravenir.entities.Author;

public interface AuthorRepository
	extends JpaRepository<Author, Long> {

    Collection<AuthorDetail> findAllProjectedBy();

    @Query("SELECT a.id AS id, a.firstName AS firstName, a.lastName AS lastName, a.authorCode AS authorCode FROM Author a JOIN BookAuthor ba ON a.id=ba.author.id WHERE ba.book.id = :bookId")
    List<AuthorDetail> retrieveBookAuthors(
	    @Param("bookId") Long id);

}