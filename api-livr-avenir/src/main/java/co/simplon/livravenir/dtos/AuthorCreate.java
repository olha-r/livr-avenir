package co.simplon.livravenir.dtos;

public class AuthorCreate {

    private String firstName;

    private String lastName;

    public AuthorCreate() {

    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    @Override
    public String toString() {
	return String.format("{firstName=%s, lastName=%s}",
		firstName, lastName);
    }

}
