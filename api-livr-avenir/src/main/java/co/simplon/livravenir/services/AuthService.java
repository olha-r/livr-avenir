package co.simplon.livravenir.services;

import co.simplon.livravenir.dtos.Credentials;
import co.simplon.livravenir.dtos.LoginCredentials;
import co.simplon.livravenir.dtos.TokenInfo;

public interface AuthService {

    void signUp(Credentials inputs);

    TokenInfo signIn(LoginCredentials inputs);

    void verifyUserRegistration(String confirmationToken);

}
