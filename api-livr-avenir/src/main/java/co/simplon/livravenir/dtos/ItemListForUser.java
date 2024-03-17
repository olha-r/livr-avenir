package co.simplon.livravenir.dtos;

import java.time.LocalDateTime;

public interface ItemListForUser {

    String getId();

    LabelValue getCondition();

    LocalDateTime getAddedAt();

    long getPointsPrice();

    String getDescription();

    Long getBookId();

    UserInfo getUser();

    BookInfo getBook();

}
