package co.simplon.livravenir.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "verification_tokens")
public class VerificationToken extends AbstractEntity {

    @Column(name = "token")
    private String token;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public VerificationToken() {
	this.expiresAt = LocalDateTime.now()
		.plusMinutes(15);
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public LocalDateTime getExpiresAt() {
	return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
	this.expiresAt = expiresAt;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Override
    public String toString() {
	return String.format(
		"{token=%s, expiresAt=%s, user=%s}", token,
		expiresAt, user);
    }

    @Override
    public int hashCode() {
	return 0;
    }

    @Override
    public boolean equals(Object obj) {
	return false;
    }

}
