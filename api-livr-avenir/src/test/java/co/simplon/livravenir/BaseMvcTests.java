package co.simplon.livravenir;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Sql(scripts = {
	"classpath:db/schema-tests.ddl.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
@Import(ControllerMocks.class)
class BaseMvcTests {

    protected static final char DELIMITER = '§';

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Tokens tokens;

    protected final ResultActions perform(String method,
	    String path, String tokenName)
	    throws Exception {
	return perform(method, path, tokenName, null);
    }

    protected final ResultActions perform(String method,
	    String path, String tokenName, String content)
	    throws Exception {
	var builder = requestBuilder(method, path,
		tokenName, content);
	return mvc.perform(builder);
    }

    private MockHttpServletRequestBuilder requestBuilder(
	    String method, String path, String tokenName,
	    String content) {
	var builder = request(HttpMethod.valueOf(method),
		path);
	if (!"anonymous".equals(tokenName)) {
	    builder.header("Authorization",
		    tokens.get(tokenName));
	}
	if (null != content) {
	    builder.contentType(MediaType.APPLICATION_JSON)
		    .content(content);
	}
	return builder;
    }

}