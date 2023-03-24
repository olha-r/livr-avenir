package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.repositories.LanguageRepository;

@Service
@Transactional(readOnly = true)
public class LanguageServiceImpl
	implements LanguageService {

    private LanguageRepository languages;

    public LanguageServiceImpl(
	    LanguageRepository languages) {
	this.languages = languages;
    }

    @Override
    public Collection<LabelValue> findAll() {
	return languages
		.findAllProjectedByOrderByLogicalOrder();
    }

}
