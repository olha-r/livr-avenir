package co.simplon.livravenir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {

    @Column(name = "category_name")
    private String categoryName;

    public Category() {
	// TODO Auto-generated constructor stub
    }

    public String getCategoryName() {
	return categoryName;
    }

    @SuppressWarnings("unused")
    public void setCategoryName(String categoryName) {
	// Not updatable
	this.categoryName = categoryName;
    }

}
