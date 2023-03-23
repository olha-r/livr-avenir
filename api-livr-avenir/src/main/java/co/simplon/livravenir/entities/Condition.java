package co.simplon.livravenir.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "conditions")
public class Condition extends AbstractEntity {

    private String name;

    private int logicalOrder;

    public Condition() {
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

    public int getLogicalOrder() {
	return logicalOrder;
    }

    @SuppressWarnings("unused")
    public void setLogicalOrder(int logicalOrder) {
	// Not updatable
	this.logicalOrder = logicalOrder;
    }

    @Override
    public String toString() {
	return "{name=" + name + ", logicalOrder="
		+ logicalOrder + "}";
    }

}
