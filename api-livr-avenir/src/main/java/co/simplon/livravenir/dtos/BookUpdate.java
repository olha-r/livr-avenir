package co.simplon.livravenir.dtos;

import java.util.Set;

public class BookUpdate {

    private String isbn;

    private String title;

    private String publicationYear;

    private String pageCount;

    private String summary;

    /* private MultipartFile coverImageUrl; */

    private Long publisherId;

    private Long categoryId;

    private Long userId;

    private Set<Long> languageIdList;

    private Set<Long> authorIdList;

    public BookUpdate() {
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

    /*
     * public MultipartFile getCoverImageUrl() { return coverImageUrl; }
     * 
     * public void setCoverImageUrl( MultipartFile coverImageUrl) {
     * this.coverImageUrl = coverImageUrl; }
     */

    public Long getPublisherId() {
	return publisherId;
    }

    public void setPublisherId(Long publisherId) {
	this.publisherId = publisherId;
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

    public Set<Long> getAuthorIdList() {
	return authorIdList;
    }

    public void setAuthorIdList(Set<Long> authorIdList) {
	this.authorIdList = authorIdList;
    }

    public Long getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(Long categoryId) {
	this.categoryId = categoryId;
    }

}
