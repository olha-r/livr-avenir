package co.simplon.livravenir.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.configuration.SecurityHelper;
import co.simplon.livravenir.dtos.BookItemCreate;
import co.simplon.livravenir.dtos.BookItemUpdate;
import co.simplon.livravenir.dtos.ItemListForBook;
import co.simplon.livravenir.dtos.ItemListForUser;
import co.simplon.livravenir.entities.Book;
import co.simplon.livravenir.entities.BookItem;
import co.simplon.livravenir.entities.Condition;
import co.simplon.livravenir.entities.User;
import co.simplon.livravenir.repositories.BookItemRepository;
import co.simplon.livravenir.repositories.BookRepository;
import co.simplon.livravenir.repositories.ConditionRepository;
import co.simplon.livravenir.repositories.UserRepository;
import jakarta.validation.Valid;

@Transactional(readOnly = true)
@Service
public class BookItemServiceImpl
	implements BookItemService {

    private final BookItemRepository bookItems;

    private final ConditionRepository conditions;

    private final BookRepository books;

    private UserRepository users;

    public BookItemServiceImpl(BookItemRepository bookItems,
	    ConditionRepository conditions,
	    BookRepository books, UserRepository users) {
	this.bookItems = bookItems;
	this.conditions = conditions;
	this.books = books;
	this.users = users;
    }

    @Transactional
    @Override
    public void createItem(BookItemCreate inputs) {
	BookItem entity = new BookItem();
	entity.setDescription(inputs.description());
	Book book = books.getReferenceById(inputs.bookId());
	entity.setBook(book);
	entity.setPointsPrice(inputs.pointsPrice());
	Condition condition = conditions
		.getReferenceById(inputs.conditionId());
	entity.setCondition(condition);

	String uuid = String.valueOf(UUID.randomUUID());
	entity.setItemCode(uuid);

	User user = null;
	Long authenticatedUserId = SecurityHelper
		.getCurrentAuthenticatedUser();
	if (authenticatedUserId != null) {
	    user = users
		    .getReferenceById(authenticatedUserId);
	}
	entity.setSeller(user);

	LocalDateTime now = LocalDateTime.now();
	entity.setAddedAt(now);
	bookItems.save(entity);
    }

    @Override
    public Set<ItemListForBook> getAllItemsByBook(
	    Long bookId) {
	Set<ItemListForBook> itemList = bookItems
		.findItemsByBookIdOrderByAddedAtDesc(
			bookId);

	return itemList;
    }

    @Override
    public Set<ItemListForUser> getItemsByUserId() {
	Long authenticatedUserId = SecurityHelper
		.getCurrentAuthenticatedUser();

	return bookItems.getBookItemsBySellerId(
		authenticatedUserId);
    }

    @Transactional
    @Override
    public void deleteBookItem(Long id) {
	Optional<BookItem> entityOptional = bookItems
		.findById(id);
	if (entityOptional.isPresent()) {
	    bookItems.deleteById(id);
	}
    }

    @Transactional
    @Override
    public void updateBookItem(Long id,
	    @Valid BookItemUpdate inputs) {
	Optional<BookItem> entityOptional = bookItems
		.findById(id);
	if (entityOptional.isPresent()) {
	    BookItem entity = entityOptional.get();
	    entity.setDescription(inputs.getDescription());
	    Condition condition = conditions
		    .getReferenceById(
			    inputs.getConditionId());
	    entity.setCondition(condition);
	    entity.setPointsPrice(inputs.getPointsPrice());
	}
    }

}
