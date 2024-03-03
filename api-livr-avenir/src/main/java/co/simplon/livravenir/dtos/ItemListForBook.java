package co.simplon.livravenir.dtos;

import java.time.LocalDateTime;

public interface ItemListForBook {

    String getId();

    LabelValue getCondition();

    LocalDateTime getAddedAt();

    long getPointsPrice();

    String getDescription();

    UserInfo getUser();

}
