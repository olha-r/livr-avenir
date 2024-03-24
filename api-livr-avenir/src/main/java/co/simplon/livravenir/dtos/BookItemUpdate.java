package co.simplon.livravenir.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookItemUpdate {

    @NotBlank
    @Size(min = 10, max = 150)
    private String description;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Long pointsPrice;

    @NotNull
    @Positive
    private Long conditionId;

    public BookItemUpdate() {

    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Long getPointsPrice() {
	return pointsPrice;
    }

    public void setPointsPrice(Long pointsPrice) {
	this.pointsPrice = pointsPrice;
    }

    public Long getConditionId() {
	return conditionId;
    }

    public void setConditionId(Long conditionId) {
	this.conditionId = conditionId;
    }

    @Override
    public String toString() {
	return String.format(
		"{description=%s, pointsPrice=%s, conditionId=%s}",
		description, pointsPrice, conditionId);
    }

}
