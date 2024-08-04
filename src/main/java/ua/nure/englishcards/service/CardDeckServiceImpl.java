package ua.nure.englishcards.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.englishcards.persistence.entity.Card;
import ua.nure.englishcards.persistence.entity.CardDeck;
import ua.nure.englishcards.persistence.entity.CardTopic;
import ua.nure.englishcards.persistence.entity.User;
import ua.nure.englishcards.persistence.repository.CardDeckRepository;
import ua.nure.englishcards.persistence.repository.CardTopicRepository;
import ua.nure.englishcards.persistence.repository.UserRepository;
import ua.nure.englishcards.service.model.CardDeckModel;
import ua.nure.englishcards.service.model.CardModel;
import ua.nure.englishcards.service.model.CardTopicModel;
import ua.nure.englishcards.service.model.NewCardDeckModel;
import ua.nure.englishcards.service.model.UserModel;

/**
 * Service implementation for managing card decks.
 * Provides methods for creating, retrieving, and listing card decks.
 */
@Service
@RequiredArgsConstructor
public class CardDeckServiceImpl implements CardDeckService {

  private final CardDeckRepository cardDeckRepository;
  private final CardTopicRepository cardTopicRepository;
  private final UserRepository userRepository;

  /**
   * Creates a new card deck.
   *
   * @param newCardDeckModel the model containing the details of the new card deck
   * @return the created card deck model
   */
  @Transactional
  @Override
  public CardDeckModel createNewCardDeck(NewCardDeckModel newCardDeckModel) {
    CardDeck cardDeck = new CardDeck();

    CardTopic cardTopic = cardTopicRepository.getReferenceById(newCardDeckModel.cardTopic().id());
    User user = userRepository.getReferenceById(newCardDeckModel.userId());

    cardDeck.setCardTopic(cardTopic);
    cardDeck.setUser(user);
    cardDeck.setName(newCardDeckModel.name());
    cardDeck.setDescription(newCardDeckModel.description());

    CardDeck savedCardDeck = cardDeckRepository.save(cardDeck);

    return getCardDeckModel(savedCardDeck);
  }

  /**
   * Retrieves a card deck by its UUID.
   *
   * @param id the UUID of the card deck to retrieve
   * @return the retrieved card deck model
   * @throws NotFoundCardDeckException if the card deck is not found
   */
  @Override
  public CardDeckModel getCardDeckByUuid(UUID id) throws NotFoundCardDeckException {
    CardDeck cardDeck = cardDeckRepository.findById(id)
        .orElseThrow(() -> new NotFoundCardDeckException("Not found card deck by id = " + id));

    return getCardDeckModel(cardDeck);
  }

  /**
   * Retrieves a card deck by its name.
   *
   * @param name the name of the card deck to retrieve
   * @return the retrieved card deck model
   * @throws NotFoundCardDeckException if the card deck is not found
   */
  @Override
  public CardDeckModel getCardDeckByName(String name) throws NotFoundCardDeckException {
    CardDeck cardDeck = cardDeckRepository.findCardDeckByName(name)
        .orElseThrow(() -> new NotFoundCardDeckException("Not found card deck by name = " + name));

    return getCardDeckModel(cardDeck);
  }

  /**
   * Retrieves a paginated list of card decks by a specific topic.
   *
   * @param cardTopic the topic associated with the card decks to retrieve
   * @param offset    the offset of the first card deck to retrieve
   * @param limit     the maximum number of card decks to retrieve
   * @return a list of card deck models
   */
  @Override
  public List<CardDeckModel> getCardDecksByTopic(CardTopicModel cardTopic, int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);
    CardTopic cardTopicReference = cardTopicRepository.getReferenceById(cardTopic.id());

    Page<CardDeck> cardDecks =
        cardDeckRepository.findCardDecksByCardTopic(cardTopicReference, pageable);

    return cardDecks.stream().map(CardDeckServiceImpl::getCardDeckModel).toList();
  }

  /**
   * Retrieves a paginated list of card decks by a specific user.
   *
   * @param user   the user associated with the card decks to retrieve
   * @param offset the offset of the first card deck to retrieve
   * @param limit  the maximum number of card decks to retrieve
   * @return a list of card deck models
   */
  @Override
  public List<CardDeckModel> getCardDeckByUser(UserModel user, int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);
    var cardDecks = cardDeckRepository.findCardDecksByUserId(user.id(), pageable);

    return cardDecks.stream().map(CardDeckServiceImpl::getCardDeckModel).toList();
  }

  /**
   * Retrieves a paginated list of all card decks.
   *
   * @param offset the offset of the first card deck to retrieve
   * @param limit  the maximum number of card decks to retrieve
   * @return a list of card deck models
   */
  @Override
  public List<CardDeckModel> getCardDecks(int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);

    var cardDecks = cardDeckRepository.findAll(pageable);

    return cardDecks.stream().map(CardDeckServiceImpl::getCardDeckModel).toList();
  }

  /**
   * Converts a card deck entity to a card deck model.
   *
   * @param cardDeck the card deck entity to convert
   * @return the converted card deck model
   */
  private static CardDeckModel getCardDeckModel(CardDeck cardDeck) {
    return new CardDeckModel(
        cardDeck.getId(),
        cardDeck.getName(),
        cardDeck.getDescription(),
        cardDeck.getCards().stream().map(CardDeckServiceImpl::getCardModel).toList(),
        getCardTopicModel(cardDeck.getCardTopic()),
        getUserModel(cardDeck.getUser())
    );
  }

  /**
   * Converts a card entity to a card model.
   *
   * @param foundCard the card entity to convert
   * @return the converted card model
   */
  private static CardModel getCardModel(Card foundCard) {
    return new CardModel(
        foundCard.getId(),
        foundCard.getWord(),
        foundCard.getTranslation(),
        foundCard.getExplanation(),
        foundCard.getExplanationTranslation(),
        foundCard.getCardDeck().getId()
    );
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

  /**
   * Converts a user entity to a user model.
   *
   * @param user the user entity to convert
   * @return the converted user model
   */
  private static UserModel getUserModel(User user) {
    return new UserModel(
        user.getId(),
        user.getEmail(),
        user.getNickname()
    );
  }

}
