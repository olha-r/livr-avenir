package co.simplon.livravenir.services;

import java.util.ArrayList;
import java.util.List;
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

	Book savedBook = books.save(entity);

	for (Long authorId : inputs.getAuthorList()) {
	    Author author = authors
		    .getReferenceById(authorId);
	    BookAuthor bookAuthorsEntity = new BookAuthor();
	    bookAuthorsEntity.setAuthor(author);
	    bookAuthorsEntity.setBook(savedBook);
	    bookAuthorsRepo.save(bookAuthorsEntity);
	}

	MultipartFile file = inputs.getCoverImageUrl();
	String baseName = UUID.randomUUID().toString();
	String fileName = storage.store(file, baseName);
	entity.setCoverImageUrl(fileName);

	books.save(entity);

    }

    @Override
    public List<BookItemView> getAllBooks() {
	List<BookItem> bookList = books
		.findAllBooksProjectedBy();
	List<BookItemView> bookItemList = new ArrayList<>();
	for (BookItem book : bookList) {
	    List<AuthorDetail> authorList = authors
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

    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
	books.deleteById(id);
    }

    @Override
    public BookView getdBookDetail(Long id) {
	BookDetail book = books.findProjectedById(id);
	List<AuthorDetail> authorList = authors
		.retrieveBookAuthors(id);
	BookView bookDetail = new BookView();
	bookDetail.setAuthorList(authorList);
	bookDetail.setBook(book);
	return bookDetail;
    }

    @Override
    public List<BookItemAdminView> getAllBooksForAdmin() {
	List<BookItemAdmin> bookList = books
		.findAllProjectedBy();
	List<BookItemAdminView> bookItemList = new ArrayList<>();
	for (BookItemAdmin book : bookList) {
	    List<AuthorDetail> authorList = authors
		    .retrieveBookAuthors(book.getId());

	    BookItemAdminView view = new BookItemAdminView();

	    view.setListAuthor(authorList);
	    view.setBook(book);
	    bookItemList.add(view);
	}
	return bookItemList;
    }

}