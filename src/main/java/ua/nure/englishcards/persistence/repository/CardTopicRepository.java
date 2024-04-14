package ua.nure.englishcards.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.englishcards.persistence.entity.CardTopic;

public interface CardTopicRepository extends JpaRepository<CardTopic, UUID> {
}
