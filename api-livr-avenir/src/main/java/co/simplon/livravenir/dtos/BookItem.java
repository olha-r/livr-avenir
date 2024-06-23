package co.simplon.livravenir.dtos;

import java.time.LocalDateTime;

public interface BookItem {

    Long getId();

    String getTitle();

    String getCoverImageUrl();

    LocalDateTime getAddedAt();

}
