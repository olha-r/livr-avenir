package co.simplon.livravenir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Language extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "logical_order")
    private int logicalOrder;

    public Language() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getLogicalOrder() {
	return logicalOrder;
    }

    @SuppressWarnings("unused")
    public void setLogicalOrder(int logicalOrder) {
	// not updatable
	this.logicalOrder = logicalOrder;
    }

    @Override
    public String toString() {
	return String.format(
		"{name=%s, logicalOrder=%s, getId()=%s}",
		name, logicalOrder, getId());
    }

}
