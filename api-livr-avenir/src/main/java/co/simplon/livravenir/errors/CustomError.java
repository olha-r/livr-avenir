package co.simplon.livravenir.errors;

final class CustomError {

    private final String code;

    private final String fieldName;

    public CustomError(String code, String fieldName,
	    String errMsg) {
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
	return String.format("{code=%s, fieldName=%s}",
		code, fieldName);
    }

}
