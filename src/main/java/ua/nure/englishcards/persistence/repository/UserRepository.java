package ua.nure.englishcards.persistence.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.englishcards.persistence.entity.User;

/**
 * The class represents the repository for the {@code User} entity that has the CRUD operation.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

  /**
   * Receives user by email.
   *
   * @param email is a user email
   * @return optional of user
   */
  Optional<User> findByEmail(String email);

}
