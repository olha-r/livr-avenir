package co.simplon.livravenir.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {

    private String name;

    public Category() {
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
