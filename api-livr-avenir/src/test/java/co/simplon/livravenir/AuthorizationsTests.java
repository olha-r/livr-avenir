package co.simplon.livravenir;

import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class AuthorizationsTests extends BaseMvcTests {

    @DisplayName("Should be authorized")
    @ParameterizedTest
    @CsvFileSource(resources = "/csv/auth/authorized.csv", delimiter = DELIMITER, numLinesToSkip = 1)
    void shouldBeAuthorized(String method, String path,
	    String tokenName) throws Exception {
	perform(method, path, tokenName).andExpectAll(
		status().is(not(401)),
		status().is(not(403)));
    }

    @DisplayName("Should be unauthorized")
    @ParameterizedTest
    @CsvFileSource(resources = "/csv/auth/unauthorized.csv", delimiter = DELIMITER, numLinesToSkip = 1)
    void shouldBeUnauthorized(String method, String path,
	    String tokenName, int expectedStatus)
	    throws Exception {
	perform(method, path, tokenName)
		.andExpect(status().is(expectedStatus));
    }

}
