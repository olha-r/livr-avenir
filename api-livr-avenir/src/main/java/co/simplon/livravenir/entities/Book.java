package co.simplon.livravenir.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
	    CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "book_authors", joinColumns = {
	    @JoinColumn(name = "book_id") }, inverseJoinColumns = {
		    @JoinColumn(name = "author_id") })
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
	    CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "book_languages", joinColumns = {
	    @JoinColumn(name = "book_id") }, inverseJoinColumns = {
		    @JoinColumn(name = "language_id") })
    private Set<Language> languages = new HashSet<>();

    public Book() {
	// TODO Auto-generated constructor stub
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

    public Set<Author> getAuthors() {
	return authors;
    }

    public void setAuthors(Set<Author> authors) {
	this.authors = authors;
    }

    public Set<Language> getLanguages() {
	return languages;
    }

    public void setLanguages(Set<Language> languages) {
	this.languages = languages;
    }

}
