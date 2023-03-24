package co.simplon.livravenir.errors;

public final class CustomError {

    private final String code;

    private final String fieldName;

    public CustomError(String code, String fieldName) {
	this.code = code;
	this.fieldName = fieldName;
    }

    public String getCode() {
	return code;
    }

    public String getFieldName() {
	return fieldName;
    }

    @Override
    public String toString() {
	return "{code=" + code + ", fieldName=" + fieldName
		+ "}";
    }

}
