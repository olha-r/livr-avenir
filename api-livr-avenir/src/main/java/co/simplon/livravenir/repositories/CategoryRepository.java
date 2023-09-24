package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.Category;

public interface CategoryRepository
	extends JpaRepository<Category, Long> {

}