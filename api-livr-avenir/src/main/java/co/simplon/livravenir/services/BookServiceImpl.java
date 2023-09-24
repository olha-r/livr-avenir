package co.simplon.livravenir.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.entities.Author;
import co.simplon.livravenir.entities.Book;
import co.simplon.livravenir.entities.Category;
import co.simplon.livravenir.entities.Language;
import co.simplon.livravenir.entities.Publisher;
import co.simplon.livravenir.entities.User;
import co.simplon.livravenir.repositories.AuthorRepository;
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

    @Value("${livravenir.uploads.location}")
    private String uploadDir;

    public BookServiceImpl(BookRepository books,
	    CategoryRepository categories,
	    PublisherRepository publishers,
	    UserRepository users, AuthorRepository authors,
	    LanguageRepository languages) {
	this.books = books;
	this.categories = categories;
	this.publishers = publishers;
	this.users = users;
	this.authors = authors;
	this.languages = languages;
    }

    @Transactional
    @Override
    public void createBook(BookCreate inputs) {
	Book entity = new Book();
	System.out.println("service" + inputs);
	entity.setIsbn(inputs.getIsbn());
	entity.setTitle(inputs.getTitle());
	entity.setPublicationYear(
		inputs.getPublicationYear());
	entity.setPageCount(inputs.getPageCount());
	entity.setSummary(inputs.getSummary());

	if ((inputs.getCoverImageUrl() != null)) {
	    MultipartFile file = inputs.getCoverImageUrl();
	    String baseName = UUID.randomUUID().toString();
	    String imageName = baseName
		    + inputs.getCoverImageUrl()
			    .getOriginalFilename();
	    entity.setCoverImageUrl(imageName);
	    store(file, imageName);
	}

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

	entity.setAuthors(new HashSet<>(authorList));

	List<Language> languageList = languages
		.findAllById(inputs.getLanguageIdList());

	entity.setLanguages(new HashSet<>(languageList));
	books.save(entity);

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

}
