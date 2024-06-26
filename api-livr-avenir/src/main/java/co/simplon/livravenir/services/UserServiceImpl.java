package co.simplon.livravenir.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.livravenir.configuration.SecurityHelper;
import co.simplon.livravenir.dtos.UserDetails;
import co.simplon.livravenir.dtos.UserProfile;
import co.simplon.livravenir.repositories.UserRepository;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository users;

    public UserServiceImpl(UserRepository users) {
	this.users = users;
    }

    @Override
    public UserProfile getUserDetails() {
	UserProfile user = null;
	Long authenticatedUserId = SecurityHelper
		.getCurrentAuthenticatedUser();
	if (authenticatedUserId != null) {
	    user = users.getUserById(authenticatedUserId);
	}
	return user;
    }

    @Override
    public List<UserDetails> getAllUsers() {
	return users
		.findAllUsersByRoleNameOrderByRegistrationDate(
			"USER");
    }

}
