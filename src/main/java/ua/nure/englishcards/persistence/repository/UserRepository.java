package ua.nure.englishcards.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.englishcards.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}
