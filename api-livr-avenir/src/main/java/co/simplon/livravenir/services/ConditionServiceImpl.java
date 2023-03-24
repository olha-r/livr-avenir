package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.repositories.ConditionRepository;

@Service
@Transactional(readOnly = true)
public class ConditionServiceImpl
	implements ConditionService {

    private final ConditionRepository conditions;

    public ConditionServiceImpl(
	    ConditionRepository conditions) {
	this.conditions = conditions;
    }

    @Override
    public Collection<LabelValue> findAll() {
	return conditions
		.findAllProjectedByOrderByLogicalOrder();
    }

}
