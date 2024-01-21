package co.simplon.livravenir.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language extends AbstractEntity {

    @Column(name = "code_iso")
    private String codeIso;

    @Column(name = "language_name")
    private String name;

    @Column(name = "logical_order")
    private long logicalOrder;

    public Language() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
	this.name = name;
    }

    public String getCodeIso() {
	return codeIso;
    }

    @SuppressWarnings("unused")
    public void setCodeIso(String codeIso) {
	this.codeIso = codeIso;
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
		"{codeIso=%s, name=%s, logicalOrder=%s}",
		codeIso, name, logicalOrder);
    }

    @Override
    public int hashCode() {
	return Objects.hash(codeIso);
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
	return Objects.equals(codeIso, other.codeIso);
    }

}
