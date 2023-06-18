package co.simplon.livravenir.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.customValidation.ImageSize;
import co.simplon.livravenir.customValidation.ImageType;

public class BookCreate {

    @NotBlank
    @Size(min = 1, max = 20)
    private String isbn;

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    @Size(max = 150)
    private String author;

    @NotNull
    private String publicationYear;

    @NotBlank
    @Size(max = 300)
    private String edition;

    @ImageType
    @ImageSize(maxValue = 2097152L)
    private MultipartFile image;

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

    public BookCreate() {
	// Required no-arg constructor
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

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getPublicationYear() {
	return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
	this.publicationYear = publicationYear;
    }

    public String getEdition() {
	return edition;
    }

    public void setEdition(String edition) {
	this.edition = edition;
    }

    public MultipartFile getImage() {
	return image;
    }

    public void setImage(MultipartFile image) {
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
	return String.format(
		"{isbn=%s, title=%s, author=%s, publicationYear=%s, edition=%s, image=%s, description=%s, point=%s, categoryId=%s, conditionId=%s, formatId=%s, languageId=%s}",
		isbn, title, author, publicationYear,
		edition, image, description, point,
		categoryId, conditionId, formatId,
		languageId);
    }

}
