package co.simplon.livravenir.dtos;

import java.util.Set;

public class BookItemView {

    private BookItem book;

    private Set<AuthorDetail> listAuthor;

    public BookItemView() {
	//
    }

    public BookItem getBook() {
	return book;
    }

    public void setBook(BookItem book) {
	this.book = book;
    }

    public Set<AuthorDetail> getListAuthor() {
	return listAuthor;
    }

    public void setListAuthor(
	    Set<AuthorDetail> listAuthor) {
	this.listAuthor = listAuthor;
    }

    @Override
    public String toString() {
	return String.format("{book=%s, listAuthor=%s}",
		book, listAuthor);
    }

}
