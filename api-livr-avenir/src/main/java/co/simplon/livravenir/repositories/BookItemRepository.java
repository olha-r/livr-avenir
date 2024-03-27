package co.simplon.livravenir.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import co.simplon.livravenir.dtos.ItemListForBook;
import co.simplon.livravenir.dtos.ItemListForUser;
import co.simplon.livravenir.entities.BookItem;

public interface BookItemRepository
	extends JpaRepository<BookItem, Long> {

    Set<ItemListForBook> findItemsByBookIdOrderByAddedAtDesc(
	    @Param("bookId") Long bookId);

    Set<ItemListForUser> getBookItemsBySellerId(
	    @Param("seller") Long seller);

}
