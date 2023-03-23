package co.simplon.livravenir.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.entities.Format;

public interface FormatRepository
	extends JpaRepository<Format, Long> {

    Collection<LabelValue> findAllProjectedBy();

}
