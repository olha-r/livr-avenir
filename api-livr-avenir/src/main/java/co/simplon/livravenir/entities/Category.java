package co.simplon.livravenir.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_code")
    private String code;

    public Category() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
	this.name = name;
    }

    public String getCode() {
	return code;
    }

    @SuppressWarnings("unused")
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
	if (!(obj instanceof Category)) {
	    return false;
	}
	Category other = (Category) obj;
	return Objects.equals(code, other.code);
    }

}
