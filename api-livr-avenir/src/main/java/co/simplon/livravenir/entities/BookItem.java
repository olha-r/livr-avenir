package co.simplon.livravenir.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_items")
public class BookItem extends AbstractEntity {

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "description")
    private String description;

    @Column(name = "points_price")
    private long pointsPrice;

    @Column(name = "added_at")
    private LocalDateTime addedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_id")
    private Condition condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BookItem() {
	//
    }

    public String getItemCode() {
	return itemCode;
    }

    public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public long getPointsPrice() {
	return pointsPrice;
    }

    public void setPointsPrice(long pointsPrice) {
	this.pointsPrice = pointsPrice;
    }

    public LocalDateTime getAddedAt() {
	return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
	this.addedAt = addedAt;
    }

    public Condition getCondition() {
	return condition;
    }

    public void setCondition(Condition condition) {
	this.condition = condition;
    }

    public Book getBook() {
	return book;
    }

    public void setBook(Book book) {
	this.book = book;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public String toString() {
	return String.format(
		"{itemCode=%s, description=%s, pointsPrice=%s, addedAt=%s, condition=%s, book=%s, user=%s}",
		itemCode, description, pointsPrice, addedAt,
		condition, book, user);
    }

    @Override
    public int hashCode() {
	return Objects.hash(itemCode);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Condition)) {
	    return false;
	}
	BookItem other = (BookItem) obj;
	return Objects.equals(itemCode, other.itemCode);
    }
}
