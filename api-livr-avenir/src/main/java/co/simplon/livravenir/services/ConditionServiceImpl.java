package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.repositories.ConditionRepository;

@Transactional(readOnly = true)
@Service
public class ConditionServiceImpl
	implements ConditionService {

    private final ConditionRepository conditions;

    public ConditionServiceImpl(
	    ConditionRepository conditions) {
	this.conditions = conditions;
    }

    @Override
    public Collection<LabelValue> getAllConditions() {
	return conditions.findAllProjectedBy();
    }

}
