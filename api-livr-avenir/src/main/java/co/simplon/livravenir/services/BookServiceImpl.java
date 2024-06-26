package co.simplon.livravenir.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.configuration.SecurityHelper;
import co.simplon.livravenir.dtos.AuthorDetail;
import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookForUpdate;
import co.simplon.livravenir.dtos.BookItem;
import co.simplon.livravenir.dtos.BookItemAdmin;
import co.simplon.livravenir.dtos.BookItemAdminView;
import co.simplon.livravenir.dtos.BookItemView;
import co.simplon.livravenir.dtos.BookUpdate;
import co.simplon.livravenir.dtos.BookView;
import co.simplon.livravenir.entities.Author;
import co.simplon.livravenir.entities.Book;
import co.simplon.livravenir.entities.BookAuthor;
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

    /**
     * Creates a new book entity based on the provided inputs and saves it to the
     * database.
     * 
     * @param inputs The DTO containing inputs for the book to be created.
     */
    @Transactional
    @Override
    public void createBook(BookCreate inputs) {
	// Create a new Book entity
	Book entity = createBookEntity(inputs);
	// Get authenticated user
	User user = null;
	Long authenticatedUserId = SecurityHelper
		.getCurrentAuthenticatedUser();
	if (authenticatedUserId != null) {
	    user = users
		    .getReferenceById(authenticatedUserId);
	}
	entity.setAddedByUser(user);
	MultipartFile file = inputs.coverImageUrl();
	String baseName = UUID.randomUUID().toString();
	// Upload the book cover image
	String fileName = storage.store(file, baseName);
	entity.setCoverImageUrl(fileName);
	// Save book entity
	Book savedBook = books.save(entity);
	addBookAuthors(inputs.authorList(), savedBook);
    }

    private Book createBookEntity(BookCreate inputs) {
	Book entity = new Book();
	entity.setIsbn(inputs.isbn());
	entity.setTitle(inputs.title());
	entity.setPublicationYear(inputs.publicationYear());
	entity.setPageCount(inputs.pageCount());
	entity.setSummary(inputs.summary());
	entity.setCategory(categories
		.getReferenceById(inputs.categoryId()));
	entity.setLanguage(languages
		.getReferenceById(inputs.languageId()));
	entity.setPublisher(publishers
		.getReferenceById(inputs.publisher()));
	LocalDateTime now = LocalDateTime.now();
	entity.setAddedAt(now);
	return entity;
    }

    private void addBookAuthors(Set<Long> authorList,
	    Book savedBook) {
	for (Long authorId : authorList) {
	    Author author = authors
		    .getReferenceById(authorId);
	    BookAuthor bookAuthorsEntity = new BookAuthor();
	    bookAuthorsEntity.setAuthor(author);
	    bookAuthorsEntity.setBook(savedBook);
	    bookAuthorsRepo.save(bookAuthorsEntity);
	}
    }

    @Override
    public List<BookItemView> getAllBooks() {
	List<BookItem> bookList = books
		.findAllBooksByOrderByAddedAtDesc();
	List<BookItemView> bookItemList = new ArrayList<>();
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
	Optional<Book> entityOptional = books.findById(id);
	if (entityOptional.isPresent()) {
	    Book entity = entityOptional.get();
	    updateBookEntity(entity, inputs);
	    updateBookAuthors(entity, inputs.authorList());
	}
    }

    private void updateBookEntity(Book entity,
	    BookUpdate inputs) {
	entity.setIsbn(inputs.isbn());
	entity.setTitle(inputs.title());
	entity.setPublicationYear(inputs.publicationYear());
	entity.setPageCount(inputs.pageCount());
	entity.setSummary(inputs.summary());
	entity.setCategory(categories
		.getReferenceById(inputs.categoryId()));
	entity.setLanguage(languages
		.getReferenceById(inputs.languageId()));
	entity.setPublisher(publishers
		.getReferenceById(inputs.publisher()));
	if (inputs.coverImageUrl() != null) {
	    entity.setCoverImageUrl(
		    storage.replace(inputs.coverImageUrl(),
			    UUID.randomUUID().toString(),
			    entity.getCoverImageUrl()));
	}
    }

    private void updateBookAuthors(Book entity,
	    Set<Long> authorIdInputs) {
	Set<Long> existingAuthorIdList = authors
		.retrieveBookAuthorsId(entity.getId());
	existingAuthorIdList.forEach(existingAuthorId -> {
	    if (!authorIdInputs
		    .contains(existingAuthorId)) {
		bookAuthorsRepo.deleteBookAuthorsByAuthorId(
			existingAuthorId);
	    }
	});
	authorIdInputs.forEach(authorId -> {
	    if (!existingAuthorIdList.contains(authorId)) {
		Author author = authors
			.getReferenceById(authorId);
		BookAuthor bookAuthorsEntity = new BookAuthor();
		bookAuthorsEntity.setAuthor(author);
		bookAuthorsEntity.setBook(entity);
		bookAuthorsRepo.save(bookAuthorsEntity);
	    }
	});
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
	Optional<Book> entityOptional = books.findById(id);
	if (entityOptional.isPresent()) {
	    Book entity = entityOptional.get();
	    String imageFullName = entity
		    .getCoverImageUrl();
	    bookAuthorsRepo.deleteBookAuthorsByBookId(id);
	    books.deleteById(id);
	    storage.delete(imageFullName);
	}
    }

    @Override
    public BookView getdBookDetail(Long id) {
	BookDetail book = books.findProjectedDetailById(id);
	Set<AuthorDetail> authorList = authors
		.retrieveBookAuthors(id);
	BookView bookDetail = new BookView();
	bookDetail.setAuthorList(authorList);
	bookDetail.setBook(book);
	return bookDetail;
    }

    @Override
    public List<BookItemAdminView> getAllBooksForAdmin() {
	List<BookItemAdmin> bookList = books
		.findAllByOrderByAddedAtDesc();
	List<BookItemAdminView> bookItemList = new ArrayList<>();
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

    @Override
    public BookForUpdate forUpdate(Long id) {
	BookDetail book = books.findProjectedDetailById(id);
	Set<AuthorDetail> authorList = authors
		.retrieveBookAuthors(id);
	BookForUpdate bookDetail = new BookForUpdate();
	bookDetail.setListAuthor(authorList);
	bookDetail.setBook(book);
	return bookDetail;
    }

    @Override
    public boolean isbnExist(String isbn)
	    throws UnsupportedOperationException {

	return this.books.existsByIsbn(isbn);
    }

}