package co.simplon.livravenir.customValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = UniqueIsbnValidator.class)
public @interface UniqueIsbn {

    String message() default "This ISBN already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
