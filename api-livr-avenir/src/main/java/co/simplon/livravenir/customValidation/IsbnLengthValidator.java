package co.simplon.livravenir.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnLengthValidator
	implements ConstraintValidator<IsbnLength, String> {

    @Override
    public boolean isValid(String value,
	    ConstraintValidatorContext context) {

	if (value == null) {
	    return true;
	}
	int valueLength = value.length();
	System.out.println("leng" + valueLength);
	return (valueLength == 10) || (valueLength == 13);
    }

}
