package co.simplon.livravenir.customvalidation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageSizeValidator implements
	ConstraintValidator<ImageSize, MultipartFile> {

    private long maxValue;

    @Override
    public void initialize(ImageSize annotation) {
	long value = annotation.value();
	if (value <= 0) {
	    throw new IllegalArgumentException(String
		    .format("value must be positive: %s",
			    value));
	}
	maxValue = value;
    }

    @Override
    public boolean isValid(MultipartFile imageFile,
	    ConstraintValidatorContext context) {
	if (imageFile == null) {
	    return true;
	}

	return imageFile.getSize() <= maxValue;
    }

}
