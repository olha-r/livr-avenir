package co.simplon.livravenir.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

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
