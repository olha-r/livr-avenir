package co.simplon.livravenir.customValidation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ImageTypeValidator implements
	ConstraintValidator<ImageType, MultipartFile> {

    private static final List<String> VALID_IMAGE_TYPES = new ArrayList<String>();
    static {
	VALID_IMAGE_TYPES.add("image/gif");
	VALID_IMAGE_TYPES.add("image/png");
	VALID_IMAGE_TYPES.add("image/jpeg");
    }

    @Override
    public boolean isValid(MultipartFile value,
	    ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}

	if (VALID_IMAGE_TYPES
		.contains(value.getContentType())) {
	    return true;

	}

	return false;
    }

}
