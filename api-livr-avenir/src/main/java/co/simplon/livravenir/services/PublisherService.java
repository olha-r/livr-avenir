package co.simplon.livravenir.services;

import java.util.Collection;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.dtos.PublisherCreate;

public interface PublisherService {

    Collection<LabelValue> getAllPublishers();

    void createPublisher(PublisherCreate inputs);

}
