package co.simplon.livravenir.dtos;

import java.util.List;

public class BookAuthorView {

    private BookDetail book;

    private List<AuthorDetail> authorList;

    public BookAuthorView() {
    }

    public BookDetail getBook() {
	return book;
    }

    public void setBook(BookDetail book) {
	this.book = book;
    }

    public List<AuthorDetail> getAuthorList() {
	return authorList;
    }

    public void setAuthorList(
	    List<AuthorDetail> authorList) {
	this.authorList = authorList;
    }

}
