package co.simplon.livravenir.dtos;

import java.util.List;

public class BookItemView {

    private BookItem book;

    private List<AuthorDetail> listAuthor;

    public BookItemView() {
    }

    public BookItem getBook() {
	return book;
    }

    public void setBook(BookItem book) {
	this.book = book;
    }

    public List<AuthorDetail> getListAuthor() {
	return listAuthor;
    }

    public void setListAuthor(
	    List<AuthorDetail> listAuthor) {
	this.listAuthor = listAuthor;
    }

}
