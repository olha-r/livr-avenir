package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.repositories.LanguageRepository;

@Transactional(readOnly = true)
@Service
public class LanguageServiceImpl
	implements LanguageService {

    private final LanguageRepository languages;

    public LanguageServiceImpl(
	    LanguageRepository languages) {
	this.languages = languages;
    }

    @Override
    public Collection<LabelValue> getAllLanguages() {
	return languages
		.findAllProjectedByOrderByLogicalOrder();
    }

}
