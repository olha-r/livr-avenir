package co.simplon.livravenir.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.entities.Language;

public interface LanguageRepository
	extends JpaRepository<Language, Long> {

    Collection<LabelValue> findAllProjectedByOrderByLogicalOrder();

}