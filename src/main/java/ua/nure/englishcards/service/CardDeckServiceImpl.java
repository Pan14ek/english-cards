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

@Service
@RequiredArgsConstructor
public class CardDeckServiceImpl implements CardDeckService {

  private final CardDeckRepository cardDeckRepository;
  private final CardTopicRepository cardTopicRepository;
  private final UserRepository userRepository;

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

  @Override
  public CardDeckModel getCardDeckByUuid(UUID id) throws NotFoundCardDeckException {
    CardDeck cardDeck = cardDeckRepository.findById(id)
        .orElseThrow(() -> new NotFoundCardDeckException("Not found card deck by id = " + id));

    return getCardDeckModel(cardDeck);
  }

  @Override
  public CardDeckModel getCardDeckByName(String name) throws NotFoundCardDeckException {
    CardDeck cardDeck = cardDeckRepository.findCardDeckByName(name)
        .orElseThrow(() -> new NotFoundCardDeckException("Not found card deck by name = " + name));

    return getCardDeckModel(cardDeck);
  }

  @Override
  public List<CardDeckModel> getCardDecksByTopic(CardTopicModel cardTopic, int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);
    CardTopic cardTopicReference = cardTopicRepository.getReferenceById(cardTopic.id());

    Page<CardDeck> cardDecks =
        cardDeckRepository.findCardDecksByCardTopic(cardTopicReference, pageable);

    return cardDecks.stream().map(CardDeckServiceImpl::getCardDeckModel).toList();
  }

  @Override
  public List<CardDeckModel> getCardDeckByUser(UserModel user, int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);
    var cardDecks = cardDeckRepository.findCardDecksByUserId(user.id(), pageable);

    return cardDecks.stream().map(CardDeckServiceImpl::getCardDeckModel).toList();
  }

  @Override
  public List<CardDeckModel> getCardDecks(int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);

    var cardDecks = cardDeckRepository.findAll(pageable);

    return cardDecks.stream().map(CardDeckServiceImpl::getCardDeckModel).toList();
  }

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

  private static CardTopicModel getCardTopicModel(CardTopic foundCardTopic) {
    return new CardTopicModel(foundCardTopic.getId(), foundCardTopic.getName());
  }

  private static UserModel getUserModel(User user) {
    return new UserModel(
        user.getId(),
        user.getEmail(),
        user.getNickname()
    );
  }

}
