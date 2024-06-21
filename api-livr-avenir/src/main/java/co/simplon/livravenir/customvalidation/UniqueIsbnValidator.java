package co.simplon.livravenir.customvalidation;

import co.simplon.livravenir.services.BookService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueIsbnValidator
	implements ConstraintValidator<UniqueIsbn, String> {

    private final BookService service;

    public UniqueIsbnValidator(BookService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String isbn,
	    ConstraintValidatorContext context) {
	if (isbn != null) {
	    return !this.service.isbnExist(isbn);
	}
	return true;

    }

}
