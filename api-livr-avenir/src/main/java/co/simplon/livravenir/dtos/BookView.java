package co.simplon.livravenir.dtos;

import java.util.Set;

public class BookView {

    private BookDetail book;

    private Set<AuthorDetail> authorList;

    public BookView() {
	//
    }

    public BookDetail getBook() {
	return book;
    }

    public void setBook(BookDetail book) {
	this.book = book;
    }

    public Set<AuthorDetail> getAuthorList() {
	return authorList;
    }

    public void setAuthorList(
	    Set<AuthorDetail> authorList) {
	this.authorList = authorList;
    }

    @Override
    public String toString() {
	return String.format("{book=%s, authorList=%s}",
		book, authorList);
    }

}
