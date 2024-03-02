package co.simplon.livravenir.services;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.AuthorCreate;
import co.simplon.livravenir.dtos.AuthorDetail;
import co.simplon.livravenir.entities.Author;
import co.simplon.livravenir.repositories.AuthorRepository;

@Transactional(readOnly = true)
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authors;

    public AuthorServiceImpl(AuthorRepository authors) {
	this.authors = authors;
    }

    @Override
    public Collection<AuthorDetail> getAllAuthors() {
	return authors.findAllProjectedBy();
    }

    @Transactional
    @Override
    public void createAuthor(AuthorCreate inputs) {
	Author entity = new Author();
	entity.setFirstName(inputs.getFirstName());
	entity.setLastName(inputs.getLastName());
	String uuid = String.valueOf(UUID.randomUUID());
	entity.setAuthorCode(uuid);

	authors.save(entity);
    }

}
