package co.simplon.livravenir.dtos;

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

}
