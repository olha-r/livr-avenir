package co.simplon.livravenir.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.livravenir.dtos.UserDetails;
import co.simplon.livravenir.dtos.UserProfile;
import co.simplon.livravenir.entities.User;

@Repository
public interface UserRepository
	extends JpaRepository<User, Long> {

    UserProfile getUserById(Long id);

    User findByEmail(String email);

    List<UserDetails> findAllUsersByRoleNameOrderByRegistrationDate(
	    String roleName);

    boolean existsByEmail(String email);

}
