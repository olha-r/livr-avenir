package co.simplon.livravenir;

import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Tests inputs against validation")
class InputsValidationTests extends BaseMvcTests {

    @DisplayName("Should inputs be not valid")
    @ParameterizedTest
    @CsvFileSource(resources = {
	    "/csv/validation/book-item-create-not-valid.csv",
	    "/csv/validation/sign-up-not-valid.csv",
	    "/csv/validation/sign-in-not-valid.csv",
	    "/csv/validation/author-create-valid.csv" }, numLinesToSkip = 1, delimiter = DELIMITER)
    void shouldBeNotValid(String method, String path,
	    String tokenName, String json)
	    throws Exception {
	perform(method, path, tokenName, json)
		.andExpect(status().is(400));
    }

    @DisplayName("Should inputs be valid")
    @ParameterizedTest
    @CsvFileSource(resources = {
	    "/csv/validation/book-item-create-valid.csv",
	    "/csv/validation/sign-up-valid.csv",
	    "/csv/validation/sign-in-valid.csv",
	    "/csv/validation/author-create-not-valid.csv" }, numLinesToSkip = 1, delimiter = DELIMITER)
    void shouldBeValid(String method, String path,
	    String tokenName, String json)
	    throws Exception {
	perform(method, path, tokenName, json)
		.andExpectAll(status().is(not(400)));
    }

}
