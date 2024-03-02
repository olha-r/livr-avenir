package co.simplon.livravenir.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "conditions")
public class Condition extends AbstractEntity {

    @Column(name = "condition_code")
    private String conditionCode;

    @Column(name = "condition_name")
    private String name;

    @Column(name = "logical_order")
    private long logicalOrder;

    public Condition() {
	//
    }

    public String getConditionCode() {
	return conditionCode;
    }

    @SuppressWarnings("unused")
    public void setConditionCode(String conditionCode) {
	this.conditionCode = conditionCode;
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
	this.name = name;
    }

    public long getLogicalOrder() {
	return logicalOrder;
    }

    @SuppressWarnings("unused")
    public void setLogicalOrder(long logicalOrder) {
	this.logicalOrder = logicalOrder;
    }

    @Override
    public String toString() {
	return String.format(
		"{conditionCode=%s, name=%s, logicalOrder=%s}",
		conditionCode, name, logicalOrder);
    }

    @Override
    public int hashCode() {
	return Objects.hash(conditionCode);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Condition)) {
	    return false;
	}
	Condition other = (Condition) obj;
	return Objects.equals(conditionCode,
		other.conditionCode);
    }

}
