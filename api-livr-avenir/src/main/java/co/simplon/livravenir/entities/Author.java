package co.simplon.livravenir.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "author_code")
    private String authorCode;

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

    public String getAuthorCode() {
	return authorCode;
    }

    public void setAuthorCode(String authorCode) {
	this.authorCode = authorCode;
    }

    @Override
    public String toString() {
	return String.format(
		"{firstName=%s, lastName=%s, authorCode=%s}",
		firstName, lastName, authorCode);
    }

    @Override
    public int hashCode() {
	return Objects.hash(authorCode);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Author)) {
	    return false;
	}
	Author other = (Author) obj;
	return Objects.equals(authorCode, other.authorCode);
    }

}
