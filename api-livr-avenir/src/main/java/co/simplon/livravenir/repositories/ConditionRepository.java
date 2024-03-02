package co.simplon.livravenir.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.entities.Condition;

public interface ConditionRepository
	extends JpaRepository<Condition, Long> {

    Collection<LabelValue> findAllProjectedBy();

}
