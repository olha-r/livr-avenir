package co.simplon.livravenir.dtos;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.customValidation.ImageSize;
import co.simplon.livravenir.customValidation.ImageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookUpdate {

    @NotBlank
    @Size(min = 10, max = 13)
    private String isbn;

    @NotBlank
    @Size(min = 2, max = 200)
    private String title;

    @NotBlank
    @Size(min = 4, max = 4)
    private String publicationYear;

    @NotBlank
    @Size(max = 5)
    private String pageCount;

    @NotBlank
    @Size(min = 10, max = 1000)
    private String summary;

    @ImageType({ MediaType.IMAGE_JPEG_VALUE,
	    MediaType.IMAGE_PNG_VALUE })
    @ImageSize
    private MultipartFile coverImageUrl;

    @NonNull
    @Positive
    private Long publisher;

    @NonNull
    @Positive
    private Long categoryId;

    @NonNull
    @Positive
    private Long languageId;

    @NonNull
    private Set<Long> authorList = new HashSet<>();

    public BookUpdate() {
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

    public MultipartFile getCoverImageUrl() {
	return coverImageUrl;
    }

    public void setCoverImageUrl(
	    MultipartFile coverImageUrl) {
	this.coverImageUrl = coverImageUrl;
    }

    public Long getPublisher() {
	return publisher;
    }

    public void setPublisher(Long publisher) {
	this.publisher = publisher;
    }

    public Long getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(Long categoryId) {
	this.categoryId = categoryId;
    }

    public Long getLanguageId() {
	return languageId;
    }

    public void setLanguageId(Long languageId) {
	this.languageId = languageId;
    }

    public Set<Long> getAuthorList() {
	return authorList;
    }

    public void setAuthorList(Set<Long> authorList) {
	this.authorList = authorList;
    }

    @Override
    public String toString() {
	return String.format(
		"{isbn=%s, title=%s, publicationYear=%s, pageCount=%s, summary=%s, coverImageUrl=%s, publisher=%s, categoryId=%s,  languageId=%s, authorList=%s}",
		isbn, title, publicationYear, pageCount,
		summary, coverImageUrl, publisher,
		categoryId, languageId, authorList);
    }

}
