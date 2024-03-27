package co.simplon.livravenir.services;

import java.time.LocalDateTime;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.Credentials;
import co.simplon.livravenir.dtos.TokenInfo;
import co.simplon.livravenir.entities.Role;
import co.simplon.livravenir.entities.User;
import co.simplon.livravenir.repositories.AuthRepository;
import co.simplon.livravenir.repositories.RoleRepository;
import co.simplon.livravenir.utils.AuthHelper;

@Transactional(readOnly = true)
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthHelper authHelper;

    private final AuthRepository authRepo;
    private final RoleRepository roleRepo;

    public AuthServiceImpl(AuthHelper authHelper,
	    AuthRepository authRepo,
	    RoleRepository roleRepo) {
	this.authHelper = authHelper;
	this.authRepo = authRepo;
	this.roleRepo = roleRepo;
    }

    @Transactional
    @Override
    public void signUp(Credentials inputs) {
	User user = new User();
	user.setFirstName(inputs.getFirstName());
	user.setLastName(inputs.getLastName());
	user.setEmail(inputs.getEmail());

	String hashPassword = authHelper
		.encode(inputs.getPassword());
	user.setPassword(hashPassword);

	Role role = roleRepo
		.getReferenceByCode("ROLE_USER");
	user.setRole(role);
	LocalDateTime registrationDate = LocalDateTime
		.now();
	user.setRegistrationDate(registrationDate);
	user.setPointsNumber(5);
	authRepo.save(user);

    }

    @Transactional
    @Override
    public TokenInfo signIn(Credentials inputs) {
	String identifier = inputs.getEmail();
	String candidate = inputs.getPassword();
	User user = authRepo.getByEmail(identifier);
	if (user != null) {
	    boolean match = authHelper.matches(candidate,
		    user.getPassword());
	    if (match) {
		String userId = Long.toString(user.getId());
		String email = user.getEmail();
		String role = user.getRole().getName();
		String token = authHelper.createJWT(role,
			userId, email);

		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setToken(token);
		tokenInfo.setRole(role);
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		tokenInfo.setFirstName(firstName);
		tokenInfo.setLastName(lastName);

		return tokenInfo;
	    } else {
		throw new BadCredentialsException(
			"Wrong credentials");
	    }
	} else {
	    throw new BadCredentialsException(
		    "Wrong credentials");
	}

    }

}
