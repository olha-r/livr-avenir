package co.simplon.livravenir.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginCredentials {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public LoginCredentials() {
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
	return String.format("{email=%s, password=%s}",
		email, "{protected}");
    }

}
