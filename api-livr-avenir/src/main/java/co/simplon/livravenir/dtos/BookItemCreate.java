package co.simplon.livravenir.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookItemCreate(
	@NotBlank @Size(min = 10, max = 150) String description,
	@NotNull @Positive Long bookId,
	@NotNull @Min(value = 1) @Max(value = 5) Long pointsPrice,
	@NotNull @Positive Long conditionId) {

}
