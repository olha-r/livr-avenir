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

    private final LanguageService service;

    public LanguageController(LanguageService service) {
	this.service = service;
    }

    @GetMapping
    public Collection<LabelValue> findAll() {
	return service.findAll();
    }

}
