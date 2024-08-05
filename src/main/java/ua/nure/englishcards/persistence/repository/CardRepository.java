package ua.nure.englishcards.persistence.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.englishcards.persistence.entity.Card;

/**
 * Repository interface for managing cards.
 * Provides CRUD operations for cards and a method to find a card by its word.
 */
public interface CardRepository extends JpaRepository<Card, UUID> {

  /**
   * Finds a card by its word.
   *
   * @param word the word associated with the card
   * @return an Optional containing the found card, or empty if no card is found
   */
  Optional<Card> findCardByWord(String word);
}
