package co.simplon.livravenir.dtos;

import java.util.List;

public class BookItemAdminView {

    private BookItemAdmin book;

    private List<AuthorDetail> listAuthor;

    public BookItemAdminView() {
    }

    public BookItemAdmin getBook() {
	return book;
    }

    public void setBook(BookItemAdmin book) {
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
