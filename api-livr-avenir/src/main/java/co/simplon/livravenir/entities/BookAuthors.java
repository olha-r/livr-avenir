package co.simplon.livravenir.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_authors")
public class BookAuthors extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authorId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookId;

    public BookAuthors() {
    }

    public Author getAuthorId() {
	return authorId;
    }

    public void setAuthorId(Author authorId) {
	this.authorId = authorId;
    }

    public Book getBookId() {
	return bookId;
    }

    public void setBookId(Book bookId) {
	this.bookId = bookId;
    }

    @Override
    public String toString() {
	return String.format("{authorId=%s, bookId=%s}",
		authorId, bookId);
    }

}
