package co.simplon.livravenir.dtos;

public class TokenInfo {

    private String token;

    private String role;

    public TokenInfo() {
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

    @Override
    public String toString() {
	return String.format("{token=%s, role=%s}", token,
		role);
    }

}
