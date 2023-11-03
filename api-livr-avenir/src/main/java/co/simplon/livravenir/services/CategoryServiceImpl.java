package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.repositories.CategoryRepository;

@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl
	implements CategoryService {

    private final CategoryRepository categories;

    public CategoryServiceImpl(
	    CategoryRepository categories) {
	this.categories = categories;
    }

    @Override
    public Collection<LabelValue> getAllCategories() {

	return categories.findAllProjectedBy();
    }

}
