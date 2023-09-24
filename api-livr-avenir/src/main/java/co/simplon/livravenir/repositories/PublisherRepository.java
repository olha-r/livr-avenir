package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.Publisher;

public interface PublisherRepository
	extends JpaRepository<Publisher, Long> {

}