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
    private String languageName;

    public Language() {
	// TODO Auto-generated constructor stub
    }

    public String getLanguageName() {
	return languageName;
    }

    @SuppressWarnings("unused")
    public void setLanguageName(String languageName) {
	// Not updatable
	this.languageName = languageName;
    }

    public String getCodeIso() {
	return codeIso;
    }

    public void setCodeIso(String codeIso) {
	this.codeIso = codeIso;
    }

}
