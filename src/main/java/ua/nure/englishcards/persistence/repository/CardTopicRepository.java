package ua.nure.englishcards.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.englishcards.persistence.entity.CardTopic;

/**
 * Repository interface for managing card topics.
 * Provides CRUD operations for card topics.
 */
public interface CardTopicRepository extends JpaRepository<CardTopic, UUID> {
}
