package co.simplon.livravenir.dtos;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.livravenir.customvalidation.ImageSize;
import co.simplon.livravenir.customvalidation.ImageType;
import co.simplon.livravenir.customvalidation.IsbnLength;
import co.simplon.livravenir.customvalidation.UniqueIsbn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookCreate(
	@NotBlank @IsbnLength @UniqueIsbn String isbn,
	@NotBlank @Size(min = 2, max = 200) String title,
	@NotBlank @Size(min = 4, max = 4) String publicationYear,
	@NotBlank @Size(max = 5) String pageCount,
	@NotBlank @Size(min = 10, max = 1000) String summary,
	@NotNull @ImageType( {
		MediaType.IMAGE_JPEG_VALUE,
		MediaType.IMAGE_PNG_VALUE }) @ImageSize MultipartFile coverImageUrl,
	@NotNull @Positive Long publisher,
	@NotNull @Positive Long categoryId,
	@NotNull @Positive Long languageId,
	@NotNull Set<Long> authorList){

}