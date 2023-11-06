package co.simplon.livravenir.dtos;

public interface BookForUpdate {

    String getIsbn();

    String getTitle();

    String getPublicationYear();

    String getPageCount();

    String getSummary();

    String getCoverImageUrl();

    LabelValue getCategory();

    LabelValue getPublisher();

    LabelValue getLanguage();

}
