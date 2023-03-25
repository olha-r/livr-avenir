package co.simplon.livravenir.services;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	entity.setName(inputs.getName());
	entity.setAuthor(inputs.getAuthor());

	LocalDate createdAt = LocalDate.now();
	entity.setCreatedAt(createdAt);
	entity.setDescription(inputs.getDescription());
	entity.setEdition(inputs.getEdition());
	entity.setImage(inputs.getImage());
	entity.setPoint(inputs.getPoint());
	entity.setYear(inputs.getYear());

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
    public Collection<BookItem> getAll() {
	return books.findAllProjectedBy();
    }

    @Override
    @Transactional
    public void update(Long id, BookUpdate inputs) {
	Book entity = books.findById(id).get();
	entity.setIsbn(inputs.getIsbn());
	entity.setName(inputs.getName());
	entity.setAuthor(inputs.getAuthor());
	Category category = categories
		.getReferenceById(inputs.getCategoryId());
	entity.setCategory(category);
	Condition condition = conditions
		.getReferenceById(inputs.getConditionId());
	entity.setCondition(condition);
	entity.setDescription(inputs.getDescription());
	entity.setEdition(inputs.getEdition());
	Format format = formats
		.getReferenceById(inputs.getFormatId());
	entity.setFormat(format);
	entity.setImage(inputs.getImage());
	Language language = languages
		.getReferenceById(inputs.getLanguageId());
	entity.setLanguage(language);
	entity.setPoint(inputs.getPoint());
	entity.setYear(inputs.getYear());
	LocalDate updatedAt = LocalDate.now();
	entity.setUpdatedAt(updatedAt);
    }

    @Transactional
    @Override
    public void delete(Long id) {
	books.deleteById(id);
    }

}
