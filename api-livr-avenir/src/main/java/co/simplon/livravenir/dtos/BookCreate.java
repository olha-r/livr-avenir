package co.simplon.livravenir.dtos;

import java.util.HashSet;
import java.util.Set;

public class BookCreate {

    private String isbn;

    private String title;

    private String publicationYear;

    private String pageCount;

    private String summary;

    /* private MultipartFile coverImageUrl; */

    private PublisherCreate publisher;

    private Long categoryId;

    private Long userId;

    private Set<Long> languageIdList = new HashSet<>();

    private Set<AuthorCreate> authorList = new HashSet<>();

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

    /*
     * public MultipartFile getCoverImageUrl() { return coverImageUrl; }
     * 
     * public void setCoverImageUrl( MultipartFile coverImageUrl) {
     * this.coverImageUrl = coverImageUrl; }
     */

    public PublisherCreate getPublisher() {
	return publisher;
    }

    public void setPublisher(PublisherCreate publisher) {
	this.publisher = publisher;
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    public Set<Long> getLanguageIdList() {
	return languageIdList;
    }

    public void setLanguageIdList(
	    Set<Long> languageIdList) {
	this.languageIdList = languageIdList;
    }

    public Set<AuthorCreate> getAuthorList() {
	return authorList;
    }

    public void setAuthorList(
	    Set<AuthorCreate> authorList) {
	this.authorList = authorList;
    }

    public Long getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(Long categoryId) {
	this.categoryId = categoryId;
    }

}
