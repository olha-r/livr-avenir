package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.livravenir.entities.User;

@Repository
public interface AuthRepository
	extends JpaRepository<User, Long> {

    User getByEmail(String identifier);

}
