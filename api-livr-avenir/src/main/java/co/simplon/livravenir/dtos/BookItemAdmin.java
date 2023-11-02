package co.simplon.livravenir.dtos;

public interface BookItemAdmin {

    Long getId();

    String getIsbn();

    String getTitle();

    String getPublicationYear();

    String getCoverImageUrl();

    LabelValue getCategory();

    LabelValue getPublisher();

    LabelValue getLanguage();

}
