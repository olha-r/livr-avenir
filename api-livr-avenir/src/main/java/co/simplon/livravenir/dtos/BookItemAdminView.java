package co.simplon.livravenir.dtos;

import java.util.Set;

public class BookItemAdminView {

    private BookItemAdmin book;

    private Set<AuthorDetail> listAuthor;

    public BookItemAdminView() {
    }

    public BookItemAdmin getBook() {
	return book;
    }

    public void setBook(BookItemAdmin book) {
	this.book = book;
    }

    public Set<AuthorDetail> getListAuthor() {
	return listAuthor;
    }

    public void setListAuthor(
	    Set<AuthorDetail> listAuthor) {
	this.listAuthor = listAuthor;
    }

}
