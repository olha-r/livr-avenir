package co.simplon.livravenir.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.dtos.AuthorCreate;
import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItemList;
import co.simplon.livravenir.dtos.BookUpdate;
import co.simplon.livravenir.dtos.PublisherCreate;
import co.simplon.livravenir.entities.Author;
import co.simplon.livravenir.entities.Book;
import co.simplon.livravenir.entities.BookAuthors;
import co.simplon.livravenir.entities.Category;
import co.simplon.livravenir.entities.Language;
import co.simplon.livravenir.entities.Publisher;
import co.simplon.livravenir.entities.User;
import co.simplon.livravenir.repositories.AuthorRepository;
import co.simplon.livravenir.repositories.BookAuthorsRepository;
import co.simplon.livravenir.repositories.BookRepository;
import co.simplon.livravenir.repositories.CategoryRepository;
import co.simplon.livravenir.repositories.LanguageRepository;
import co.simplon.livravenir.repositories.PublisherRepository;
import co.simplon.livravenir.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository books;

    private final CategoryRepository categories;

    private final PublisherRepository publishers;

    private final AuthorRepository authors;

    private final LanguageRepository languages;

    private final UserRepository users;

    private final BookAuthorsRepository bookAuthorsRepo;

    @Value("${livravenir.uploads.location}")
    private String uploadDir;

    public BookServiceImpl(BookRepository books,
	    CategoryRepository categories,
	    PublisherRepository publishers,
	    UserRepository users, AuthorRepository authors,
	    LanguageRepository languages,
	    BookAuthorsRepository bookAuthorsRepo) {

	this.books = books;
	this.categories = categories;
	this.publishers = publishers;
	this.users = users;
	this.authors = authors;
	this.languages = languages;
	this.bookAuthorsRepo = bookAuthorsRepo;

    }

    @Transactional
    @Override
    public void createBook(BookCreate inputs) {
	Book entity = new Book();
	entity.setIsbn(inputs.getIsbn());
	entity.setTitle(inputs.getTitle());
	entity.setPublicationYear(
		inputs.getPublicationYear());
	entity.setPageCount(inputs.getPageCount());
	entity.setSummary(inputs.getSummary());

	Category category = categories
		.getReferenceById(inputs.getCategoryId());
	entity.setCategory(category);

	/*
	 * PublisherCreate publisher = inputs.getPublisher(); if (publisher != null) {
	 * Optional<Publisher> optionalPublisher = publishers
	 * .findByName(publisher.getName()); if (optionalPublisher.isEmpty()) {
	 * createPublisher(publisher); }
	 * 
	 * } Publisher publisherId = publishers
	 * .findPublisherByName(publisher.getName()); entity.setPublisher(publisherId);
	 */

	Language language = languages
		.getReferenceById(inputs.getLanguageId());
	entity.setLanguage(language);

	Publisher publisher = publishers
		.getReferenceById(inputs.getPublisher());
	entity.setPublisher(publisher);

	User user = users
		.getReferenceById(inputs.getUserId());
	entity.setUser(user);

	Book savedBook = books.save(entity);

	for (Long authorId : inputs.getAuthorList()) {
	    Author author = authors
		    .getReferenceById(authorId);
	    BookAuthors bookAuthorsEntity = new BookAuthors();
	    bookAuthorsEntity.setAuthorId(author);
	    bookAuthorsEntity.setBookId(savedBook);
	    bookAuthorsRepo.save(bookAuthorsEntity);
	}

	/*
	 * Set<Author> authorList = new HashSet<>(); Set<AuthorCreate> inputsAuthorList
	 * = inputs .getAuthorList(); if (inputsAuthorList != null) { for (AuthorCreate
	 * authorCreate : inputsAuthorList) { Optional<Author> authorOptional = authors
	 * .findByFirstNameAndLastName( authorCreate.getFirstName(),
	 * authorCreate.getLastName()); if (authorOptional.isEmpty()) {
	 * createAuthor(authorCreate); } Optional<Author> allAuthors = authors
	 * .findByFirstNameAndLastName( authorCreate.getFirstName(),
	 * authorCreate.getLastName()); allAuthors.ifPresent(authorList::add); } }
	 * entity.setAuthors(authorList);
	 */

	/*
	 * MultipartFile file = inputs.getCoverImageUrl();
	 * 
	 * String baseName = UUID.randomUUID().toString(); String imageName = baseName +
	 * inputs .getCoverImageUrl().getOriginalFilename();
	 * entity.setCoverImageUrl(imageName); System.out.println(entity);
	 * System.out.println(imageName); store(file, imageName);
	 */

    }

    private void createPublisher(PublisherCreate inputs) {
	Publisher entity = new Publisher();
	entity.setName(inputs.getName());
	String uuid = String.valueOf(UUID.randomUUID());
	entity.setCode(uuid);
	publishers.save(entity);

    }

    private void createAuthor(AuthorCreate inputs) {
	Author entity = new Author();
	entity.setFirstName(inputs.getFirstName());
	entity.setLastName(inputs.getLastName());
	String uuid = String.valueOf(UUID.randomUUID());
	entity.setAuthorCode(uuid);
	authors.save(entity);
    }

    private void store(MultipartFile file,
	    String fileName) {
	Path uploadPath = Paths.get(uploadDir);
	Path target = uploadPath.resolve(fileName);
	try (InputStream in = file.getInputStream()) {

	    Files.copy(in, target,
		    StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}

    }

    @Override
    public Collection<BookItemList> getAllBooks() {
	// TODO Auto-generated method stub
	return books.findAllBooksProjectedBy();
    }

    @Transactional
    @Override
    public void updateBook(Long id, BookUpdate inputs) {
	Book entity = books.findById(id).get();
	entity.setIsbn(inputs.getIsbn());
	entity.setTitle(inputs.getTitle());
	entity.setPublicationYear(
		inputs.getPublicationYear());
	entity.setPageCount(inputs.getPageCount());
	entity.setSummary(inputs.getSummary());
	Category category = categories
		.getReferenceById(inputs.getCategoryId());
	entity.setCategory(category);
	Publisher publisher = publishers
		.getReferenceById(inputs.getPublisherId());
	entity.setPublisher(publisher);
	User user = users
		.getReferenceById(inputs.getUserId());
	entity.setUser(user);
	List<Author> authorList = authors
		.findAllById(inputs.getAuthorIdList());
	/* entity.setAuthors(new HashSet<>(authorList)); */

	Language language = languages
		.getReferenceById(inputs.getLanguageId());
	entity.setLanguage(language);
	/*
	 * MultipartFile file = inputs.getCoverImageUrl(); if (file != null) { String
	 * baseName = UUID.randomUUID().toString(); String imageName = baseName +
	 * file.getOriginalFilename(); entity.setCoverImageUrl(imageName); store(file,
	 * imageName); }
	 */

    }

    @Override
    public BookDetail getdBookDetail(Long id) {
	return books.findProjectedById(id);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
	books.deleteById(id);
    }

}