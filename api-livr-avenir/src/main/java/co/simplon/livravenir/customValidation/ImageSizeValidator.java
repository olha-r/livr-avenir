package co.simplon.livravenir.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ImageSizeValidator implements
	ConstraintValidator<ImageSize, MultipartFile> {

    private long maxValue;

    @Override
    public void initialize(ImageSize maxValue) {
	this.maxValue = maxValue.maxValue();
    }

    @Override
    public boolean isValid(MultipartFile value,
	    ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}

	if (value.getSize() < maxValue) {
	    return true;

	}

	return false;
    }

}
