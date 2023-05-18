package co.simplon.livravenir.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends AbstractEntity {

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private LocalDate year;

    @Column(name = "edition")
    private String edition;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "format_id")
    private Format format;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_id")
    private Condition condition;

    private int point;

    private LocalDateTime createdAt;

    private LocalDate updatedAt;

    public Book() {
	// TODO Auto-generated constructor stub
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public LocalDate getYear() {
	return year;
    }

    public void setYear(LocalDate year) {
	this.year = year;
    }

    public String getEdition() {
	return edition;
    }

    public void setEdition(String edition) {
	this.edition = edition;
    }

    public String getImage() {
	return image;
    }

    public void setImage(String image) {
	this.image = image;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Format getFormat() {
	return format;
    }

    public void setFormat(Format format) {
	this.format = format;
    }

    public Language getLanguage() {
	return language;
    }

    public void setLanguage(Language language) {
	this.language = language;
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public Condition getCondition() {
	return condition;
    }

    public void setCondition(Condition condition) {
	this.condition = condition;
    }

    public int getPoint() {
	return point;
    }

    public void setPoint(int point) {
	this.point = point;
    }

    public LocalDateTime getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
    }

    @PrePersist
    private void createdAt() {
	setCreatedAt(LocalDateTime.now());
    }

    public LocalDate getUpdatedAt() {
	return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
	this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
	return String.format(
		"{isbn=%s, name=%s, author=%s, year=%s, edition=%s, image=%s, description=%s, format=%s, language=%s, category=%s, condition=%s, point=%s, createdAt=%s, updatedAt=%s}",
		getId(), isbn, name, author, year, edition,
		image, description, "LAZY_LOADED",
		"LAZY_LOADED", "LAZY_LOADED", "LAZY_LOADED",
		point, createdAt, updatedAt);
    }

}