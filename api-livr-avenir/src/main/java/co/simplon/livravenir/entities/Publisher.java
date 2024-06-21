package co.simplon.livravenir.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher extends AbstractEntity {

    @Column(name = "publisher_name")
    private String name;

    @Column(name = "publisher_code")
    private String code;

    public Publisher() {
	//
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    @Override
    public String toString() {
	return String.format("{name=%s, code=%s}", name,
		code);
    }

    @Override
    public int hashCode() {
	return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Publisher)) {
	    return false;
	}
	Publisher other = (Publisher) obj;
	return Objects.equals(code, other.code);
    }

}
