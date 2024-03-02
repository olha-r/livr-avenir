package co.simplon.livravenir.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends AbstractEntity {

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "publication_year")
    private String publicationYear;

    @Column(name = "page_count")
    private String pageCount;

    @Column(name = "summary")
    private String summary;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    public Book() {
	//
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getPublicationYear() {
	return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
	this.publicationYear = publicationYear;
    }

    public String getPageCount() {
	return pageCount;
    }

    public void setPageCount(String pageCount) {
	this.pageCount = pageCount;
    }

    public String getSummary() {
	return summary;
    }

    public void setSummary(String summary) {
	this.summary = summary;
    }

    public String getCoverImageUrl() {
	return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
	this.coverImageUrl = coverImageUrl;
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public Publisher getPublisher() {
	return publisher;
    }

    public void setPublisher(Publisher publisher) {
	this.publisher = publisher;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Language getLanguage() {
	return language;
    }

    public void setLanguage(Language language) {
	this.language = language;
    }

    @Override
    public String toString() {
	return String.format(
		"{isbn=%s, title=%s, publicationYear=%s, pageCount=%s, summary=%s, coverImageUrl=%s, category=%s, publisher=%s, user=%s, language=%s}",
		isbn, title, publicationYear, pageCount,
		summary, coverImageUrl, category, publisher,
		user, language);
    }

    @Override
    public int hashCode() {
	return Objects.hash(isbn);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Book)) {
	    return false;
	}
	Book other = (Book) obj;
	return Objects.equals(isbn, other.isbn);
    }

}
