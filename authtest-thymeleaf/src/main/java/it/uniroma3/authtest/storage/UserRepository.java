package it.uniroma3.authtest.storage;

import it.uniroma3.authtest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a JpaRepository for storage operations on Users.
 *
 * @see User
 */
public interface UserRepository extends JpaRepository<User, Long> {

}