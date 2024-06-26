package co.simplon.livravenir.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.UserDetails;
import co.simplon.livravenir.dtos.UserProfile;
import co.simplon.livravenir.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
	this.userService = userService;
    }

    @GetMapping("/profile")
    public UserProfile getUserDetails() {
	return userService.getUserDetails();
    }

    @GetMapping
    public List<UserDetails> getAllUsers() {
	return userService.getAllUsers();
    }

}
