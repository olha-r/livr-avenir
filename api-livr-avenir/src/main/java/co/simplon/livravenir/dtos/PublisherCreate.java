package co.simplon.livravenir.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublisherCreate(
	@NotBlank @Size(min = 2, max = 200) String name) {
}
