package co.simplon.livravenir.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.services.LanguageService;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(
	    LanguageService languageService) {
	this.languageService = languageService;
    }

    @GetMapping
    public Collection<LabelValue> getAllLanguages() {
	return languageService.getAllLanguages();
    }
}
