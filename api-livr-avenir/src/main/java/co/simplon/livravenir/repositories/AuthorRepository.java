package co.simplon.livravenir.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.dtos.AuthorDetail;
import co.simplon.livravenir.entities.Author;

public interface AuthorRepository
	extends JpaRepository<Author, Long> {

    Optional<Author> findByFirstNameAndLastName(
	    String firstName, String lastName);

    Collection<AuthorDetail> findAllProjectedBy();

}