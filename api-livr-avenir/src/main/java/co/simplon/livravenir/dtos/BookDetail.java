package co.simplon.livravenir.dtos;

import co.simplon.livravenir.entities.Category;
import co.simplon.livravenir.entities.Publisher;

public interface BookDetail {

    Long getId();

    String getIsbn();

    String getTitle();

    String getCoverImageUrl();

    String getPublicationYear();

    String getPageCount();

    String getSummary();

    Publisher getPublisher();

    Category getCategory();

}
