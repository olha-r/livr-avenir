package co.simplon.livravenir.customvalidation;

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

    String message() default "{livravenir.validation.constraints.ImageType.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
