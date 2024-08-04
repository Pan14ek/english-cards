package ua.nure.englishcards.service;

import java.util.List;
import java.util.UUID;
import ua.nure.englishcards.service.model.CardModel;
import ua.nure.englishcards.service.model.NewCardModel;

/**
 * Service interface for managing cards.
 * Provides methods for creating, updating, retrieving, and listing cards.
 */
public interface CardService {

  /**
   * Saves a new card.
   *
   * @param newCardModel the model containing the details of the new card
   * @return the saved card model
   */
  CardModel saveNewCard(NewCardModel newCardModel);

  /**
   * Updates an existing card.
   *
   * @param cardModel the model containing the updated details of the card
   * @return the updated card model
   * @throws NotFoundCardException if the card is not found
   */
  CardModel updateCard(CardModel cardModel) throws NotFoundCardException;

  /**
   * Retrieves a card by its ID.
   *
   * @param id the UUID of the card to retrieve
   * @return the retrieved card model
   * @throws NotFoundCardException if the card is not found
   */
  CardModel getCardById(UUID id) throws NotFoundCardException;

  /**
   * Retrieves a card by a word associated with it.
   *
   * @param word the word associated with the card to retrieve
   * @return the retrieved card model
   * @throws NotFoundCardException if the card is not found
   */
  CardModel getCardByWord(String word) throws NotFoundCardException;

  /**
   * Retrieves a paginated list of cards.
   *
   * @param offset the offset of the first card to retrieve
   * @param limit  the maximum number of cards to retrieve
   * @return a list of card models
   */
  List<CardModel> getCards(int offset, int limit);

}
