package ua.nure.englishcards.persistence.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.englishcards.persistence.entity.Card;

public interface CardRepository extends JpaRepository<Card, UUID> {

  Optional<Card> findCardByWord(String word);

}
