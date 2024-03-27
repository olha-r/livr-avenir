package co.simplon.livravenir.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "points_number")
    private long pointsNumber;

    public User() {

    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
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

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public LocalDateTime getRegistrationDate() {
	return registrationDate;
    }

    public void setRegistrationDate(
	    LocalDateTime registrationDate) {
	this.registrationDate = registrationDate;
    }

    public long getPointsNumber() {
	return pointsNumber;
    }

    public void setPointsNumber(long pointsNumber) {
	this.pointsNumber = pointsNumber;
    }

    @Override
    public String toString() {
	return String.format(
		"{email=%s, firstName=%s, lastName=%s, role=%s, registrationDate=%s, pointsNumber=%s}",
		email, firstName, lastName, role,
		registrationDate, pointsNumber);
    }

    @Override
    public int hashCode() {
	return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof User)) {
	    return false;
	}
	User other = (User) obj;
	return Objects.equals(email, other.email);
    }

}
