package co.simplon.livravenir.dtos;

import java.time.LocalDateTime;

public interface UserDetails {

    Long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    long getPointsNumber();

    LocalDateTime getRegistrationDate();

    boolean getIsEnabled();

}
