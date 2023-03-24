package co.simplon.livravenir.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.services.ConditionService;

@RestController
@RequestMapping("/conditions")
public class ConditionController {

    private final ConditionService service;

    public ConditionController(ConditionService service) {
	this.service = service;
    }

    @GetMapping
    public Collection<LabelValue> findAll() {
	return service.findAll();
    }

}
