package co.simplon.livravenir.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.entities.Category;

@Repository
public interface CategoryRepository
	extends JpaRepository<Category, Long> {

    Collection<LabelValue> findAllProjectedBy();

}