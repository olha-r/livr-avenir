package co.simplon.livravenir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
