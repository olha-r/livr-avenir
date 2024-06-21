package co.simplon.livravenir.customvalidation;

import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageTypeValidator implements
	ConstraintValidator<ImageType, MultipartFile> {

    private String[] imageTypes;

    @Override
    public void initialize(ImageType annotation) {
	imageTypes = annotation.value();
    }

    @Override
    public boolean isValid(MultipartFile imageFile,
	    ConstraintValidatorContext context) {
	if (imageFile == null) {
	    return true;
	}
	String contentType = imageFile.getContentType();
	return Arrays.stream(imageTypes).anyMatch(
		type -> type.equals(MediaType.ALL_VALUE)
			|| type.equals(contentType));
    }

}
