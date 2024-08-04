package ua.nure.englishcards.service;

import java.util.List;
import java.util.UUID;
import ua.nure.englishcards.service.model.CardDeckModel;
import ua.nure.englishcards.service.model.CardTopicModel;
import ua.nure.englishcards.service.model.NewCardDeckModel;
import ua.nure.englishcards.service.model.UserModel;

/**
 * Service interface for managing card decks.
 * Provides methods for creating, retrieving, and listing card decks.
 */
public interface CardDeckService {

  /**
   * Creates a new card deck.
   *
   * @param newCardDeckModel the model containing the details of the new card deck
   * @return the created card deck model
   */
  CardDeckModel createNewCardDeck(NewCardDeckModel newCardDeckModel);

  /**
   * Retrieves a card deck by its UUID.
   *
   * @param id the UUID of the card deck to retrieve
   * @return the retrieved card deck model
   * @throws NotFoundCardDeckException if the card deck is not found
   */
  CardDeckModel getCardDeckByUuid(UUID id) throws NotFoundCardDeckException;

  /**
   * Retrieves a card deck by its name.
   *
   * @param name the name of the card deck to retrieve
   * @return the retrieved card deck model
   * @throws NotFoundCardDeckException if the card deck is not found
   */
  CardDeckModel getCardDeckByName(String name) throws NotFoundCardDeckException;

  /**
   * Retrieves a paginated list of card decks by a specific topic.
   *
   * @param cardTopic the topic associated with the card decks to retrieve
   * @param offset    the offset of the first card deck to retrieve
   * @param limit     the maximum number of card decks to retrieve
   * @return a list of card deck models
   */
  List<CardDeckModel> getCardDecksByTopic(CardTopicModel cardTopic, int offset, int limit);

  /**
   * Retrieves a paginated list of card decks by a specific user.
   *
   * @param user   the user associated with the card decks to retrieve
   * @param offset the offset of the first card deck to retrieve
   * @param limit  the maximum number of card decks to retrieve
   * @return a list of card deck models
   */
  List<CardDeckModel> getCardDeckByUser(UserModel user, int offset, int limit);

  /**
   * Retrieves a paginated list of all card decks.
   *
   * @param offset the offset of the first card deck to retrieve
   * @param limit  the maximum number of card decks to retrieve
   * @return a list of card deck models
   */
  List<CardDeckModel> getCardDecks(int offset, int limit);

}
