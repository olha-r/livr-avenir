package co.simplon.livravenir.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.entities.Publisher;

public interface PublisherRepository
	extends JpaRepository<Publisher, Long> {

    Collection<LabelValue> findAllProjectedBy();

    Optional<Publisher> findByName(String publisher);

    Publisher findPublisherByName(String name);

}