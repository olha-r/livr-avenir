package co.simplon.livravenir.dtos;

public class Credentials {

    private String identifier;

    private String password;

    public Credentials() {
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return String.format("{identifier=%s, password=%s}",
		identifier, "{protected}");
    }

}
