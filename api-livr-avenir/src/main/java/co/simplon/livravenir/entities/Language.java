package co.simplon.livravenir.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Language extends AbstractEntity {

    @Column(name = "code_iso")
    private String codeIso;

    @Column(name = "language_name")
    private String name;

    @Column(name = "logical_order")
    private long logicalOrder;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
	    CascadeType.PERSIST,
	    CascadeType.MERGE }, mappedBy = "languages")
    private Set<Book> books = new HashSet<>();

    public Language() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
	this.name = name;
    }

    public String getCodeIso() {
	return codeIso;
    }

    @SuppressWarnings("unused")
    public void setCodeIso(String codeIso) {
	this.codeIso = codeIso;
    }

    public Set<Book> getBooks() {
	return books;
    }

    public void setBooks(Set<Book> books) {
	this.books = books;
    }

    public long getLogicalOrder() {
	return logicalOrder;
    }

    @SuppressWarnings("unused")
    public void setLogicalOrder(long logicalOrder) {
	this.logicalOrder = logicalOrder;
    }
}
