package co.simplon.livravenir.customValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = IsbnLengthValidator.class)
public @interface IsbnLength {

    String message() default "ISBN must have 10 or 13 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
