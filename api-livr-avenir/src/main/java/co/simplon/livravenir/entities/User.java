package co.simplon.livravenir.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private Long pointsNumber;

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

    public Long getPointsNumber() {
	return pointsNumber;
    }

    public void setPointsNumber(Long pointsNumber) {
	this.pointsNumber = pointsNumber;
    }

    @Override
    public String toString() {
	return String.format(
		"{email=%s,  firstName=%s, lastName=%s, password=%s, role=%s}",
		email, firstName, lastName, password, role);
    }

}
