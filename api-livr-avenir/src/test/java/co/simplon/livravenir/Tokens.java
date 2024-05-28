package co.simplon.livravenir;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Tokens {

    @Value("${livravenir.tests.bad-secret-token}")
    private String badSecret;

    @Value("${livravenir.tests.expired-token}")
    private String expired;

    @Value("${livravenir.tests.valid-admin-token}")
    private String admin;

    @Value("${livravenir.tests.valid-user-token}")
    private String user;

    String get(final String name) {
	switch (name) {
	case "badSecret":
	    return badSecret;
	case "expired":
	    return expired;
	case "admin":
	    return admin;
	case "user":
	    return user;
	default:
	    throw new IllegalArgumentException(
		    "Unexpected value: " + name);
	}
    }

}