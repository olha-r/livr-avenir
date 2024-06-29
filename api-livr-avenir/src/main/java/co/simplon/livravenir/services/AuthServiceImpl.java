package co.simplon.livravenir.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.dtos.Credentials;
import co.simplon.livravenir.dtos.LoginCredentials;
import co.simplon.livravenir.dtos.TokenInfo;
import co.simplon.livravenir.entities.Role;
import co.simplon.livravenir.entities.User;
import co.simplon.livravenir.entities.VerificationToken;
import co.simplon.livravenir.repositories.AuthRepository;
import co.simplon.livravenir.repositories.RoleRepository;
import co.simplon.livravenir.repositories.UserRepository;
import co.simplon.livravenir.repositories.VerificationTokenRepository;
import co.simplon.livravenir.services.helpers.MailSenderHelper;
import co.simplon.livravenir.utils.AuthHelper;

@Transactional(readOnly = true)
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthHelper authHelper;
    private final MailSenderHelper mailSenderHelper;

    private final AuthRepository authRepo;
    private final RoleRepository roleRepo;
    private final UserRepository users;
    private final VerificationTokenRepository tokenRepo;

    public AuthServiceImpl(
	    VerificationTokenRepository tokenRepo,
	    AuthHelper authHelper, AuthRepository authRepo,
	    RoleRepository roleRepo, UserRepository users,
	    MailSenderHelper mailSenderHelper) {
	this.authHelper = authHelper;
	this.authRepo = authRepo;
	this.roleRepo = roleRepo;
	this.users = users;
	this.mailSenderHelper = mailSenderHelper;
	this.tokenRepo = tokenRepo;
    }

    @Transactional
    @Override
    public void signUp(Credentials inputs) {

	String token = UUID.randomUUID().toString();

	VerificationToken verificationToken = new VerificationToken();
	verificationToken.setToken(token);

	User user = new User();
	user.setIsEnabled(false);
	user.setFirstName(inputs.firstName());
	user.setLastName(inputs.lastName());
	String userEmail = inputs.email();
	user.setEmail(userEmail);

	String hashPassword = authHelper
		.encode(inputs.password());
	user.setPassword(hashPassword);

	Role role = roleRepo
		.getReferenceByCode("ROLE_USER");
	user.setRole(role);
	LocalDateTime registrationDate = LocalDateTime
		.now();
	user.setRegistrationDate(registrationDate);
	user.setPointsNumber(5);

	authRepo.save(user);

	verificationToken.setUser(user);
	tokenRepo.save(verificationToken);

	mailSenderHelper.sendMail(userEmail, token);

    }

    @Transactional
    @Override
    public TokenInfo signIn(LoginCredentials inputs)
	    throws BadCredentialsException {
	String identifier = inputs.email();
	String candidate = inputs.password();
	User user = authRepo.getByEmail(identifier);
	if (user == null) {
	    throw new BadCredentialsException(
		    "No user found");
	}
	if (!user.getIsEnabled()) {
	    throw new BadCredentialsException(
		    "User email was not confirmed");
	}
	if (!authHelper.matches(candidate,
		user.getPassword())) {
	    throw new BadCredentialsException(
		    "Wrong credentials");
	}
	String userId = Long.toString(user.getId());
	String email = user.getEmail();
	String role = user.getRole().getName();
	String token = authHelper.createJWT(role, userId,
		email);
	TokenInfo tokenInfo = new TokenInfo();
	tokenInfo.setToken(token);
	tokenInfo.setRole(role);
	String firstName = user.getFirstName();
	String lastName = user.getLastName();
	tokenInfo.setFirstName(firstName);
	tokenInfo.setLastName(lastName);
	return tokenInfo;
    }

    @Transactional
    @Override
    public void verifyUserRegistration(
	    String confirmationToken) {
	VerificationToken verifyToken = tokenRepo
		.findByToken(confirmationToken);
	if (verifyToken != null) {
	    LocalDateTime currentTime = LocalDateTime.now();
	    LocalDateTime expiresAt = verifyToken
		    .getExpiresAt();
	    if (currentTime.isBefore(expiresAt)) {
		User user = users.findByEmail(
			verifyToken.getUser().getEmail());
		user.setIsEnabled(true);
		users.save(user);
		tokenRepo.delete(verifyToken);
	    }

	}
    }

}
