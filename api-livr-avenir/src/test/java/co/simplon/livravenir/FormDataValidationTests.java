package co.simplon.livravenir;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")

@Import(ControllerMocks.class)
public class FormDataValidationTests {

    protected static final char DELIMITER = '§';

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Tokens tokens;

    @DisplayName("Should form data inputs be not valid")
    @ParameterizedTest
    @CsvFileSource(resources = {
	    "/csv/validation/book-create-not-valid.csv" }, numLinesToSkip = 1, delimiter = DELIMITER)
    void shouldFormDataBeNotValid(String method,
	    String path, String tokenName, String formData)
	    throws Exception {
	performFormData(method, path, tokenName, formData)
		.andExpect(status().is(400));
    }

    @DisplayName("Should form data inputs be valid")
    @ParameterizedTest
    @CsvFileSource(resources = {
	    "/csv/validation/book-create-valid.csv" }, numLinesToSkip = 1, delimiter = DELIMITER)
    void shouldFormDataBeValid(String method, String path,
	    String tokenName, String formData)
	    throws Exception {
	performFormData(method, path, tokenName, formData)
		.andExpectAll(status().isNoContent());
    }

    private ResultActions performFormData(String method,
	    String path, String tokenName, String formData)
	    throws Exception {
	var builder = formRequestBuilder(method, path,
		tokenName, formData);
	return mvc.perform(builder);
    }

    private MockHttpServletRequestBuilder formRequestBuilder(
	    String method, String path, String tokenName,
	    String formData) {
	var builder = multipart(path);
	if (!"anonymous".equals(tokenName)) {
	    builder.header("Authorization",
		    tokens.get(tokenName));
	}
	if (null != formData) {
	    MultiValueMap<String, String> params = parseFormData(
		    formData);
	    for (String key : params.keySet()) {
		if (key.equals("coverImageUrl")) {
		    MockMultipartFile coverImage = new MockMultipartFile(
			    key, "cover.jpg", "image/jpeg",
			    params.getFirst(key)
				    .getBytes());
		    builder.file(coverImage);
		} else {
		    builder.param(key, params.get(key)
			    .toArray(new String[0]));
		}
	    }
	}
	builder.contentType("multipart/form-data");
	return builder;
    }

    private MultiValueMap<String, String> parseFormData(
	    String formData) {
	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	String[] pairs = formData.split("&");
	for (String pair : pairs) {
	    int idx = pair.indexOf("=");
	    params.add(pair.substring(0, idx),
		    pair.substring(idx + 1));
	}
	return params;
    }

}
