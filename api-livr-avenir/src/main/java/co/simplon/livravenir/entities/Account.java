package co.simplon.livravenir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account extends AbstractEntity {

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "password")
    private String password;

    // @Column(name = "roles")
    // private List<String> roles;

    public Account() {

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
	return String.format(
		"{identifier=%s, password=%s, roles=%s}",
		identifier, "{protected}");
    }

}
