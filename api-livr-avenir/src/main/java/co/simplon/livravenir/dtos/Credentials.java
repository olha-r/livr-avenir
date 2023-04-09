package co.simplon.livravenir.dtos;

public class Credentials {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public Credentials() {
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

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return String.format(
		"{firstName=%s, lastName=%s, email=%s, password=%s}",
		firstName, lastName, email, "{protected}");
    }

}
