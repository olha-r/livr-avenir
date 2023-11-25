package co.simplon.livravenir.configuration;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityHelper {

    private SecurityHelper() {
	// Ensures non-instantiability
    }

    /**
     * Returns the id of the authenticated user.
     *
     * @return the id of the authenticated user
     */
    public static Long getCurrentAuthenticatedUser() {
	Authentication authentication = SecurityContextHolder
		.getContext().getAuthentication();
	Long currentUserId = null;
	if (!(authentication instanceof AnonymousAuthenticationToken)) {
	    String authenticatedUserId = authentication
		    .getName();
	    currentUserId = Long
		    .parseLong(authenticatedUserId);
	}
	return currentUserId;
    }
}
