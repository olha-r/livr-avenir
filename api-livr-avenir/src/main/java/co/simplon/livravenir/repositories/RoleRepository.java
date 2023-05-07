package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.livravenir.entities.Role;

public interface RoleRepository
	extends JpaRepository<Role, Long> {

    Role getReferenceByName(String string);
}
