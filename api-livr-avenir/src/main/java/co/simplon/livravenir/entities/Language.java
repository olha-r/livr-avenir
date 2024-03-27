package co.simplon.livravenir.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language extends AbstractEntity {

    @Column(name = "iso_code")
    private String isoCode;

    @Column(name = "language_name")
    private String name;

    @Column(name = "logical_order")
    private long logicalOrder;

    public Language() {
	//
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
	this.name = name;
    }

    public String getIsoCode() {
	return isoCode;
    }

    @SuppressWarnings("unused")
    public void setIsoCode(String isoCode) {
	this.isoCode = isoCode;
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
		"{isoCode=%s, name=%s, logicalOrder=%s}",
		isoCode, name, logicalOrder);
    }

    @Override
    public int hashCode() {
	return Objects.hash(isoCode);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Language)) {
	    return false;
	}
	Language other = (Language) obj;
	return Objects.equals(isoCode, other.isoCode);
    }

}
