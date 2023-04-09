package co.simplon.livravenir.dtos;

import java.time.LocalDateTime;

public interface BookItem {

    Long getId();

    String getName();

    String getAuthor();

    String getImage();

    LocalDateTime getCreatedAt();

}
