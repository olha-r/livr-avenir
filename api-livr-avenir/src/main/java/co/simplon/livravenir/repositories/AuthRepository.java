package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.Account;

public interface AuthRepository
	extends JpaRepository<Account, Long> {

    Account getByEmail(String identifier);

}
