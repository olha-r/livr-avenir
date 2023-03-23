package co.simplon.livravenir.dtos;

import java.time.LocalDate;

public interface BookDetail {

    String getIsbn();

    String getName();

    String getAuthor();

    LocalDate getYear();

    String getEdition();

    String getImage();

    String getDescription();

    // String getFormatName();

    // String getLanguageName();

    // String getCategoryName();

    // String getConditionName();

    int getPoint();

}
