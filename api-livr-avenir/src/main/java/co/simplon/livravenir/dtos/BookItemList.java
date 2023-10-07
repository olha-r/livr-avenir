package co.simplon.livravenir.dtos;

import java.util.List;

public interface BookItemList {

    Long getId();

    String getIsbn();

    String getTitle();

    List<AuthorDetail> getAuthors();

    String getCoverImageUrl();

}
