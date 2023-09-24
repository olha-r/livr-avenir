package co.simplon.livravenir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher extends AbstractEntity {

    @Column(name = "publisher_name")
    private String publisherName;

    public Publisher() {
	// TODO Auto-generated constructor stub
    }

    public String getPublisherName() {
	return publisherName;
    }

    @SuppressWarnings("unused")
    public void setPublisherName(String publisherName) {
	// Not updatable
	this.publisherName = publisherName;
    }

}
