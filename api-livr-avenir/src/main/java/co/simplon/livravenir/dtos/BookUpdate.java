package co.simplon.livravenir.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BookUpdate {

    @NotBlank
    @Size(min = 1, max = 20)
    private String isbn;

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotBlank
    @Size(max = 150)
    private String author;

    @NotNull
    private LocalDate year;

    @NotBlank
    @Size(max = 300)
    private String edition;

    @NotBlank
    @Size(max = 150)
    private String image;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    @Positive
    @Max(value = 5)
    private int point;

    @NotNull
    @Positive
    private Long categoryId;

    @NotNull
    @Positive
    private Long conditionId;

    @NotNull
    @Positive
    private Long formatId;

    @NotNull
    @Positive
    private Long languageId;

    public BookUpdate() {
	// Required no-arg constructor
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

    public int getPoint() {
	return point;
    }

    public void setPoint(int point) {
	this.point = point;
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

    @Override
    public String toString() {
	return "{isbn=" + isbn + ", name=" + name
		+ ", author=" + author + ", year=" + year
		+ ", edition=" + edition + ", image="
		+ image + ", description=" + description
		+ ", point=" + point + ", categoryId="
		+ categoryId + ", conditionId="
		+ conditionId + ", formatId=" + formatId
		+ ", languageId=" + languageId + "}";
    }

}
