package ua.nure.englishcards.persistence.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.englishcards.persistence.entity.CardDeck;
import ua.nure.englishcards.persistence.entity.CardTopic;

/**
 * Repository interface for managing card decks.
 * Provides CRUD operations for card decks and methods to
 * find card decks by name, topic, and user ID.
 */
public interface CardDeckRepository extends JpaRepository<CardDeck, UUID> {

  /**
   * Finds a card deck by its name.
   *
   * @param name the name of the card deck
   * @return an Optional containing the found card deck, or empty if no card deck is found
   */
  Optional<CardDeck> findCardDeckByName(String name);

  /**
   * Finds card decks by their associated card topic.
   *
   * @param cardTopic the card topic associated with the card decks
   * @param pageable  the pagination information
   * @return a page of card decks
   */
  Page<CardDeck> findCardDecksByCardTopic(CardTopic cardTopic, Pageable pageable);

  /**
   * Finds card decks by their associated user ID.
   *
   * @param userId   the ID of the user associated with the card decks
   * @param pageable the pagination information
   * @return a page of card decks
   */
  Page<CardDeck> findCardDecksByUserId(UUID userId, Pageable pageable);
}
