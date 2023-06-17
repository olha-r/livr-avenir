package co.simplon.livravenir.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.dtos.BookCreate;
import co.simplon.livravenir.dtos.BookDetail;
import co.simplon.livravenir.dtos.BookItem;
import co.simplon.livravenir.dtos.BookUpdate;
import co.simplon.livravenir.entities.Book;
import co.simplon.livravenir.entities.Category;
import co.simplon.livravenir.entities.Condition;
import co.simplon.livravenir.entities.Format;
import co.simplon.livravenir.entities.Language;
import co.simplon.livravenir.repositories.BookRepository;
import co.simplon.livravenir.repositories.CategoryRepository;
import co.simplon.livravenir.repositories.ConditionRepository;
import co.simplon.livravenir.repositories.FormatRepository;
import co.simplon.livravenir.repositories.LanguageRepository;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final CategoryRepository categories;

    private final ConditionRepository conditions;

    private final FormatRepository formats;

    private final LanguageRepository languages;

    private final BookRepository books;

    @Value("${livravenir.uploads.location}")
    private String uploadDir;

    public BookServiceImpl(CategoryRepository categories,
	    ConditionRepository conditions,
	    FormatRepository formats,
	    LanguageRepository languages,
	    BookRepository books) {
	this.categories = categories;
	this.conditions = conditions;
	this.formats = formats;
	this.languages = languages;
	this.books = books;

    }

    @Transactional
    @Override
    public void create(BookCreate inputs) {
	Book entity = new Book();
	entity.setIsbn(inputs.getIsbn());
	entity.setTitle(inputs.getTitle());
	entity.setAuthor(inputs.getAuthor());

	LocalDateTime createdAt = LocalDateTime.now();
	entity.setCreatedAt(createdAt);
	entity.setDescription(inputs.getDescription());
	entity.setEdition(inputs.getEdition());

	entity.setPoint(inputs.getPoint());

	if ((inputs.getImage() != null)) {
	    MultipartFile file = inputs.getImage();
	    String baseName = UUID.randomUUID().toString();
	    String imageName = baseName + inputs.getImage()
		    .getOriginalFilename();
	    entity.setImage(imageName);
	    store(file, imageName);
	}

	Category category = categories
		.getReferenceById(inputs.getCategoryId());

	entity.setCategory(category);
	Format format = formats
		.getReferenceById(inputs.getFormatId());

	entity.setFormat(format);
	Language language = languages
		.getReferenceById(inputs.getFormatId());
	entity.setLanguage(language);

	Condition condition = conditions
		.getReferenceById(inputs.getConditionId());
	entity.setCondition(condition);
	books.save(entity);

    }

    @Override
    public BookDetail detail(Long id) {
	return books.findProjectedById(id);
    }

    @Override
    public Collection<BookItem> getTop4LastAdded() {
	return books.findTop4ByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional
    public void update(Long id, BookUpdate inputs) {
	Book entity = books.findById(id).get();
	entity.setIsbn(inputs.getIsbn());
	entity.setTitle(inputs.getTitle());
	entity.setAuthor(inputs.getAuthor());
	entity.setDescription(inputs.getDescription());
	entity.setEdition(inputs.getEdition());
	entity.setImage(inputs.getImage());
	entity.setPoint(inputs.getPoint());
	LocalDate updatedAt = LocalDate.now();
	entity.setUpdatedAt(updatedAt);
	Format format = formats
		.getReferenceById(inputs.getFormatId());
	entity.setFormat(format);
	Category category = categories
		.getReferenceById(inputs.getCategoryId());
	entity.setCategory(category);
	Language language = languages
		.getReferenceById(inputs.getLanguageId());
	entity.setLanguage(language);
	Condition condition = conditions
		.getReferenceById(inputs.getConditionId());
	entity.setCondition(condition);
    }

    @Transactional
    @Override
    public void delete(Long id) {
	books.deleteById(id);
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
