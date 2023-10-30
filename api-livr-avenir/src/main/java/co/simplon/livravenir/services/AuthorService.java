package co.simplon.livravenir.services;

import java.util.Collection;

import co.simplon.livravenir.dtos.AuthorDetail;

public interface AuthorService {

    Collection<AuthorDetail> getAllAuthors();
}
