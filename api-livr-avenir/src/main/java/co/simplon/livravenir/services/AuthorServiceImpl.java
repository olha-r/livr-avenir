package co.simplon.livravenir.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import co.simplon.livravenir.dtos.AuthorDetail;
import co.simplon.livravenir.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authors;

    public AuthorServiceImpl(AuthorRepository authors) {
	this.authors = authors;
    }

    @Override
    public Collection<AuthorDetail> getAllAuthors() {
	// TODO Auto-generated method stub
	return authors.findAllProjectedBy();
    }

}
