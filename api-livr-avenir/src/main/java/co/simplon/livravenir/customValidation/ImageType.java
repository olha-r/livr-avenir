package co.simplon.livravenir.customValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = ImageTypeValidator.class)
public @interface ImageType {

    String[] value() default { MediaType.ALL_VALUE };

    String message() default "Image type is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
