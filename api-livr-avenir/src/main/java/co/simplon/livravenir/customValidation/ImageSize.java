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
@Constraint(validatedBy = ImageSizeValidator.class)
public @interface ImageSize {

    String message() default "Image size is too large";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long maxValue();
}