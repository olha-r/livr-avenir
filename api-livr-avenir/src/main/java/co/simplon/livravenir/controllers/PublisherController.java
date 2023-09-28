package co.simplon.livravenir.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.services.PublisherService;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(
	    PublisherService publisherService) {
	this.publisherService = publisherService;
    }

    @GetMapping
    public Collection<LabelValue> getAllPublishers() {
	return publisherService.getAllPublishers();
    }
}
