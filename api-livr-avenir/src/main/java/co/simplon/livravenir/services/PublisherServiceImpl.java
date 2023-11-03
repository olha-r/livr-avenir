package co.simplon.livravenir.services;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.dtos.PublisherCreate;
import co.simplon.livravenir.entities.Publisher;
import co.simplon.livravenir.repositories.PublisherRepository;

@Transactional(readOnly = true)
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

    @Transactional
    @Override
    public void createPublisher(PublisherCreate inputs) {
	Publisher entity = new Publisher();
	entity.setName(inputs.getName());
	String uuid = String.valueOf(UUID.randomUUID());
	entity.setCode(uuid);
	publishers.save(entity);

    }

}
