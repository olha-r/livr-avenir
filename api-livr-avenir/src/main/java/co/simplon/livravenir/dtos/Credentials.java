package co.simplon.livravenir.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Credentials(
	@NotBlank @Size(min = 2, max = 30) String firstName,
	@NotBlank @Size(min = 2, max = 100) String lastName,
	@NotBlank @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") String email,
	@NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*?]).{8,42}$") String password) {

}
