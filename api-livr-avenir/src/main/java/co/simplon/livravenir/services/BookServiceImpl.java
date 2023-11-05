package co.simplon.livravenir.services;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.dtos.AuthorDetail;
import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItem;
import co.simplon.livravenir.dtos.BookItemAdmin;
import co.simplon.livravenir.dtos.BookItemAdminView;
import co.simplon.livravenir.dtos.BookItemView;
import co.simplon.livravenir.dtos.BookUpdate;
import co.simplon.livravenir.dtos.BookView;
import co.simplon.livravenir.entities.Author;
import co.simplon.livravenir.entities.Book;
import co.simplon.livravenir.entities.BookAuthor;
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

	Language language = languages
		.getReferenceById(inputs.getLanguageId());
	entity.setLanguage(language);

	Publisher publisher = publishers
		.getReferenceById(inputs.getPublisher());
	entity.setPublisher(publisher);

	User user = users
		.getReferenceById(inputs.getUserId());
	entity.setUser(user);

	MultipartFile file = inputs.getCoverImageUrl();
	String baseName = UUID.randomUUID().toString();
	String fileName = storage.store(file, baseName);
	entity.setCoverImageUrl(fileName);

	Book savedBook = books.save(entity);

	for (Long authorId : inputs.getAuthorList()) {
	    Author author = authors
		    .getReferenceById(authorId);
	    BookAuthor bookAuthorsEntity = new BookAuthor();
	    bookAuthorsEntity.setAuthor(author);
	    bookAuthorsEntity.setBook(savedBook);
	    bookAuthorsRepo.save(bookAuthorsEntity);
	}

    }

    @Override
    public Set<BookItemView> getAllBooks() {
	Set<BookItem> bookList = books
		.findAllBooksProjectedBy();
	Set<BookItemView> bookItemList = new HashSet<>();
	for (BookItem book : bookList) {
	    Set<AuthorDetail> authorList = authors
		    .retrieveBookAuthors(book.getId());

	    BookItemView view = new BookItemView();

	    view.setListAuthor(authorList);
	    view.setBook(book);
	    bookItemList.add(view);
	}

	return bookItemList;
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

	Language language = languages
		.getReferenceById(inputs.getLanguageId());
	entity.setLanguage(language);

	Publisher publisher = publishers
		.getReferenceById(inputs.getPublisher());
	entity.setPublisher(publisher);

	User user = users
		.getReferenceById(inputs.getUserId());
	entity.setUser(user);

	MultipartFile file = inputs.getCoverImageUrl();
	if (file != null) {
	    String original = entity.getCoverImageUrl();
	    String baseName = UUID.randomUUID().toString();
	    String newFullName = storage.replace(file,
		    baseName, original);
	    entity.setCoverImageUrl(newFullName);
	}

	Set<Long> authorList = authors
		.retrieveBookAuthorsId(entity.getId());

	System.out.println("Author list" + authors);

	for (Long authorId : inputs.getAuthorList()) {
	    if (!authorList.contains(authorId)) {
		Author author = authors
			.getReferenceById(authorId);
		BookAuthor bookAuthorsEntity = new BookAuthor();
		bookAuthorsEntity.setAuthor(author);
		bookAuthorsEntity.setBook(entity);
		bookAuthorsRepo.save(bookAuthorsEntity);
	    }

	}

    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
	bookAuthorsRepo.deleteBookAuthorsByBookId(id);
	books.deleteById(id);

    }

    @Override
    public BookView getdBookDetail(Long id) {
	BookDetail book = books.findProjectedById(id);
	Set<AuthorDetail> authorList = authors
		.retrieveBookAuthors(id);
	BookView bookDetail = new BookView();
	bookDetail.setAuthorList(authorList);
	bookDetail.setBook(book);
	return bookDetail;
    }

    @Override
    public Set<BookItemAdminView> getAllBooksForAdmin() {
	Set<BookItemAdmin> bookList = books
		.findAllProjectedBy();
	Set<BookItemAdminView> bookItemList = new HashSet<>();
	for (BookItemAdmin book : bookList) {
	    Set<AuthorDetail> authorList = authors
		    .retrieveBookAuthors(book.getId());

	    BookItemAdminView view = new BookItemAdminView();

	    view.setListAuthor(authorList);
	    view.setBook(book);
	    bookItemList.add(view);
	}
	return bookItemList;
    }

}