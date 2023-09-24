package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.Language;

public interface LanguageRepository
	extends JpaRepository<Language, Long> {

}