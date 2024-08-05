package ua.nure.englishcards.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.englishcards.persistence.entity.CardTopic;
import ua.nure.englishcards.persistence.repository.CardTopicRepository;
import ua.nure.englishcards.service.model.CardTopicModel;
import ua.nure.englishcards.service.model.NewCardTopicModel;

/**
 * Service implementation for managing card topics.
 * Provides methods for creating, retrieving, and listing card topics.
 */
@Service
@RequiredArgsConstructor
public class CardTopicServiceImpl implements CardTopicService {

  private final CardTopicRepository topicRepository;

  /**
   * Saves a new card topic.
   *
   * @param newCardTopicModel the model containing the details of the new card topic
   * @return the saved card topic model
   */
  @Transactional
  @Override
  public CardTopicModel saveNewTopic(NewCardTopicModel newCardTopicModel) {
    CardTopic cardTopic = new CardTopic();

    cardTopic.setName(newCardTopicModel.name());

    CardTopic savedCardTopic = topicRepository.save(cardTopic);

    return getCardTopicModel(savedCardTopic);
  }

  /**
   * Retrieves a card topic by its ID.
   *
   * @param id the UUID of the card topic to retrieve
   * @return the retrieved card topic model
   * @throws NotFoundCardTopicException if the card topic is not found
   */
  @Override
  public CardTopicModel getTopicById(UUID id) throws NotFoundCardTopicException {
    CardTopic foundCardTopic = topicRepository.findById(id)
        .orElseThrow(() -> new NotFoundCardTopicException(id.toString()));

    return getCardTopicModel(foundCardTopic);
  }

  /**
   * Retrieves a paginated list of card topics.
   *
   * @param offset the offset of the first card topic to retrieve
   * @param limit  the maximum number of card topics to retrieve
   * @return a list of card topic models
   */
  @Override
  public List<CardTopicModel> getTopics(int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);

    Page<CardTopic> topics = topicRepository.findAll(pageable);

    return topics.stream().map(CardTopicServiceImpl::getCardTopicModel).toList();
  }

  /**
   * Converts a card topic entity to a card topic model.
   *
   * @param foundCardTopic the card topic entity to convert
   * @return the converted card topic model
   */
  private static CardTopicModel getCardTopicModel(CardTopic foundCardTopic) {
    return new CardTopicModel(foundCardTopic.getId(), foundCardTopic.getName());
  }

}
