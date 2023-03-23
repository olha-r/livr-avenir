package co.simplon.livravenir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "formats")
public class Format extends AbstractEntity {

    @Column(name = "name", updatable = false)
    private String name;

    public Format() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
	// Not updatable
	this.name = name;
    }

    @Override
    public String toString() {
	return "{name=" + name + "}";
    }

}
