package co.simplon.livravenir.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Column(name = "role_name")
    private String name;

    @Column(name = "code_role")
    private String code;

    public Role() {
	// TODO Auto-generated constructor stub
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
	if (!(obj instanceof Role)) {
	    return false;
	}
	Role other = (Role) obj;
	return Objects.equals(code, other.code);
    }

}
