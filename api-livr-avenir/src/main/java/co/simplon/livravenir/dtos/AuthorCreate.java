package co.simplon.livravenir.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorCreate(
	@NotBlank @Size(min = 1, max = 150) String firstName,
	@NotBlank @Size(min = 1, max = 150) String lastName) {
}
