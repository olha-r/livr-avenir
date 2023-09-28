package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.repositories.PublisherRepository;

@Service
public class PublisherServiceImpl
	implements PublisherService {

    private final PublisherRepository publishers;

    public PublisherServiceImpl(
	    PublisherRepository publishers) {
	this.publishers = publishers;
    }

    @Override
    public Collection<LabelValue> getAllPublishers() {
	return publishers.findAllProjectedBy();
    }

}
