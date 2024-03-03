package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.livravenir.dtos.UserProfile;
import co.simplon.livravenir.entities.User;

@Repository
public interface UserRepository
	extends JpaRepository<User, Long> {

    UserProfile getUserById(Long id);

}
