package co.simplon.livravenir.dtos;

import java.time.LocalDateTime;

public interface BookItemAdmin {

    Long getId();

    String getIsbn();

    String getTitle();

    String getCoverImageUrl();

    String getPublicationYear();

    String getPageCount();

    String getSummary();

    LabelValue getCategory();

    LabelValue getPublisher();

    LabelValue getLanguage();

    LocalDateTime getAddedAt();

}
