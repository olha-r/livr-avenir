package co.simplon.livravenir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher extends AbstractEntity {

    @Column(name = "publisher_name")
    private String name;

    public Publisher() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
