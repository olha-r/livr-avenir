package co.simplon.livravenir.services;

import java.util.List;

import co.simplon.livravenir.dtos.UserDetails;
import co.simplon.livravenir.dtos.UserProfile;

public interface UserService {

    UserProfile getUserDetails();

    List<UserDetails> getAllUsers();

    boolean emailExist(String email);

}
