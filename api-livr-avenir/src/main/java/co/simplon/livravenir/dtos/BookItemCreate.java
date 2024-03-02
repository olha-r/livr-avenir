package co.simplon.livravenir.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookItemCreate(
	@NotBlank @Size(min = 10, max = 1000) String description,
	@NotNull @Positive Long bookId,
	@NotNull @Positive long pointsPrice,
	@NotNull @Positive Long conditionId) {

}
