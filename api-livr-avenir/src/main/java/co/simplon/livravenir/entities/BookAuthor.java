package co.simplon.livravenir.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_authors")
public class BookAuthor extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookAuthor() {
	//
    }

    public Book getBook() {
	return book;
    }

    public void setBook(Book book) {
	this.book = book;
    }

    public Author getAuthor() {
	return author;
    }

    public void setAuthor(Author author) {
	this.author = author;
    }

    @Override
    public int hashCode() {
	return Objects.hash(author, book);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof BookAuthor other
		&& Objects.equals(author, other.author)
		&& Objects.equals(book, other.book);
    }

    @Override
    public String toString() {
	return String.format("{author=%s, book=%s}", author,
		book);
    }

}
