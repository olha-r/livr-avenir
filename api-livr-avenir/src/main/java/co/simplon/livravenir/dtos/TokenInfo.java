package co.simplon.livravenir.dtos;

import java.util.List;

public class TokenInfo {

    private String token;

    private List<String> roles;

    public TokenInfo() {
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public List<String> getRoles() {
	return roles;
    }

    public void setRoles(List<String> roles) {
	this.roles = roles;
    }

    @Override
    public String toString() {
	return String.format("{token=%s, roles=%s}", token,
		roles);
    }

}
