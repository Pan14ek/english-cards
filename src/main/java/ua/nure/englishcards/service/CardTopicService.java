package ua.nure.englishcards.service;

import java.util.List;
import java.util.UUID;
import ua.nure.englishcards.service.model.CardTopicModel;
import ua.nure.englishcards.service.model.NewCardTopicModel;

/**
 * Service interface for managing card topics.
 * Provides methods for creating, retrieving, and listing card topics.
 */
public interface CardTopicService {

  /**
   * Saves a new card topic.
   *
   * @param newCardTopicModel the model containing the details of the new card topic
   * @return the saved card topic model
   */
  CardTopicModel saveNewTopic(NewCardTopicModel newCardTopicModel);

  /**
   * Retrieves a card topic by its ID.
   *
   * @param id the UUID of the card topic to retrieve
   * @return the retrieved card topic model
   * @throws NotFoundCardTopicException if the card topic is not found
   */
  CardTopicModel getTopicById(UUID id) throws NotFoundCardTopicException;

  /**
   * Retrieves a paginated list of card topics.
   *
   * @param offset the offset of the first card topic to retrieve
   * @param limit  the maximum number of card topics to retrieve
   * @return a list of card topic models
   */
  List<CardTopicModel> getTopics(int offset, int limit);

}
