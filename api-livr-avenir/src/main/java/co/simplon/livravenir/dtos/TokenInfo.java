package co.simplon.livravenir.dtos;

public class TokenInfo {

    private String token;

    private String role;

    private String firstName;

    private String lastName;

    public TokenInfo() {
	//
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
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
	return String.format(
		"{token=%s, role=%s, firstName=%s, lastName=%s}",
		token, role, firstName, lastName);
    }

}
