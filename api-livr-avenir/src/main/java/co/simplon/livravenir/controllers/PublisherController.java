package co.simplon.livravenir.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.dtos.PublisherCreate;
import co.simplon.livravenir.services.PublisherService;
import jakarta.validation.Valid;

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void createPublisher(
	    @Valid @RequestBody PublisherCreate inputs) {
	publisherService.createPublisher(inputs);
    }

}
