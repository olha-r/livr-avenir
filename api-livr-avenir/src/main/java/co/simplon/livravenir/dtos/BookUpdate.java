package co.simplon.livravenir.dtos;

import java.time.LocalDate;

public class BookUpdate {

    private String isbn;

    private String name;

    private String author;

    private LocalDate year;

    private String edition;

    private String image;

    private String description;

    private Long formatId;

    private Long languageId;

    private Long categoryId;

    private Long conditionId;

    private int point;

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

    public Long getFormatId() {
	return formatId;
    }

    public void setFormatId(Long formatId) {
	this.formatId = formatId;
    }

    public Long getLanguageId() {
	return languageId;
    }

    public void setLanguageId(Long languageId) {
	this.languageId = languageId;
    }

    public Long getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(Long categoryId) {
	this.categoryId = categoryId;
    }

    public Long getConditionId() {
	return conditionId;
    }

    public void setConditionId(Long conditionId) {
	this.conditionId = conditionId;
    }

    public int getPoint() {
	return point;
    }

    public void setPoint(int point) {
	this.point = point;
    }

    @Override
    public String toString() {
	return "{isbn=" + isbn + ", name=" + name
		+ ", author=" + author + ", year=" + year
		+ ", edition=" + edition + ", image="
		+ image + ", description=" + description
		+ ", formatId=" + formatId + ", languageId="
		+ languageId + ", categoryId=" + categoryId
		+ ", conditionId=" + conditionId
		+ ", point=" + point + "}";
    }

}
