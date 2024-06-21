package co.simplon.livravenir.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.Credentials;
import co.simplon.livravenir.dtos.LoginCredentials;
import co.simplon.livravenir.dtos.TokenInfo;
import co.simplon.livravenir.services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
	this.authService = authService;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUp(
	    @Valid @RequestBody Credentials inputs) {
	authService.signUp(inputs);
    }

    @PostMapping("/sign-in")
    public TokenInfo signIn(
	    @Valid @RequestBody LoginCredentials inputs) {
	return authService.signIn(inputs);
    }

    @GetMapping("/verify")
    public void verifyUserRegistration(
	    @RequestParam("code") String confirmationToken) {
	authService
		.verifyUserRegistration(confirmationToken);
    }

}
