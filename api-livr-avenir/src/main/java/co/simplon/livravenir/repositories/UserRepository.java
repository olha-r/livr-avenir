package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.User;

public interface UserRepository
	extends JpaRepository<User, Long> {

}
