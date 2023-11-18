package co.simplon.livravenir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.livravenir.entities.Role;

@Repository
public interface RoleRepository
	extends JpaRepository<Role, Long> {

    Role getReferenceByCode(String string);
}
