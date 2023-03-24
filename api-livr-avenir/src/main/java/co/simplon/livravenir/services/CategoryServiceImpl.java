package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.repositories.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl
	implements CategoryService {

    private final CategoryRepository categories;

    public CategoryServiceImpl(
	    CategoryRepository categories) {
	this.categories = categories;

    }

    @Override
    public Collection<LabelValue> getAll() {
	return categories.findAllProjectedBy();
    }

}
