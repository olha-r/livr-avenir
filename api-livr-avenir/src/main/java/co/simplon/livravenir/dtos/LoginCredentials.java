package co.simplon.livravenir.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginCredentials(@NotBlank String email,
	@NotBlank String password) {
}
