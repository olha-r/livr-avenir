package co.simplon.livravenir.dtos;

import java.time.LocalDateTime;

public interface UserProfile {

    Long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    Long getPointsNumber();

    LocalDateTime getRegistrationDate();

}
