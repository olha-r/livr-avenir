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
@Table(name = "authors")
public class Author extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
	    CascadeType.PERSIST,
	    CascadeType.MERGE }, mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Author() {
	// TODO Auto-generated constructor stub
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	// Not updatable
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Set<Book> getBooks() {
	return books;
    }

    public void setBooks(Set<Book> books) {
	this.books = books;
    }

}
