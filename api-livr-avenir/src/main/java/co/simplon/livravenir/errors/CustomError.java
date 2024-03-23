package co.simplon.livravenir.errors;

public final class CustomError {

    private final String code;

    private final String fieldName;

    private final String errMsg;

    public CustomError(String code, String fieldName,
	    String errMsg) {
	this.code = code;
	this.fieldName = fieldName;
	this.errMsg = errMsg;
    }

    public String getCode() {
	return code;
    }

    public String getFieldName() {
	return fieldName;
    }

    public String getErrMsg() {
	return errMsg;
    }

    @Override
    public String toString() {
	return String.format(
		"{code=%s, fieldName=%s, errMsg=%s}", code,
		fieldName, errMsg);
    }

}
