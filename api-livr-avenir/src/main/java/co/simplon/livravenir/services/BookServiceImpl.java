package co.simplon.livravenir.services;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItemList;
import co.simplon.livravenir.dtos.BookUpdate;
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

    private final FileStorage storage;

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
	    BookAuthorsRepository bookAuthorsRepo,
	    FileStorage storage) {

	this.books = books;
	this.categories = categories;
	this.publishers = publishers;
	this.users = users;
	this.authors = authors;
	this.languages = languages;
	this.bookAuthorsRepo = bookAuthorsRepo;
	this.storage = storage;

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

	MultipartFile file = inputs.getCoverImageUrl();
	String baseName = UUID.randomUUID().toString();
	String fileName = storage.store(file, baseName);
	entity.setCoverImageUrl(fileName);

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

	books.save(entity);

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