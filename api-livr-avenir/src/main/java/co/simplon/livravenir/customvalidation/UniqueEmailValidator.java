package co.simplon.livravenir.customvalidation;

import co.simplon.livravenir.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements
	ConstraintValidator<UniqueEmail, String> {

    private final UserService service;

    public UniqueEmailValidator(UserService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String email,
	    ConstraintValidatorContext context) {
	if (email != null) {
	    return !this.service.emailExist(email);
	}
	return true;

    }

}
