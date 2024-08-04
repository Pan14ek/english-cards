package ua.nure.englishcards.service;

import jakarta.validation.constraints.NotNull;
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
import ua.nure.englishcards.persistence.repository.CardDeckRepository;
import ua.nure.englishcards.persistence.repository.CardRepository;
import ua.nure.englishcards.service.model.CardModel;
import ua.nure.englishcards.service.model.NewCardModel;

/**
 * Service implementation for managing cards.
 * Provides methods for creating, updating, retrieving, and searching cards.
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;
  private final CardDeckRepository cardDeckRepository;

  /**
   * Saves a new card.
   *
   * @param newCardModel the model containing the details of the new card
   * @return the saved card model
   */
  @Transactional
  @Override
  public CardModel saveNewCard(NewCardModel newCardModel) {
    Card card = new Card();

    return updateCardEntity(card, newCardModel.cardDeckId(), newCardModel.word(),
        newCardModel.translation(),
        newCardModel.explanationTranslation(), newCardModel.explanation());
  }

  /**
   * Updates an existing card.
   *
   * @param cardModel the model containing the updated details of the card
   * @return the updated card model
   * @throws NotFoundCardException if the card is not found
   */
  @Transactional
  @Override
  public CardModel updateCard(CardModel cardModel) throws NotFoundCardException {
    Card foundCard = cardRepository.findById(cardModel.id()).orElseThrow(
        () -> new NotFoundCardException("Not found card by id: " + cardModel.id()));

    return updateCardEntity(foundCard, cardModel.cardDeckId(), cardModel.word(),
        cardModel.translation(), cardModel.explanationTranslation(), cardModel.explanation());
  }

  /**
   * Retrieves a card by its ID.
   *
   * @param id the UUID of the card to retrieve
   * @return the retrieved card model
   * @throws NotFoundCardException if the card is not found
   */
  @Override
  public CardModel getCardById(UUID id) throws NotFoundCardException {
    Card foundCard = cardRepository.findById(id).orElseThrow(
        () -> new NotFoundCardException("Not found card by id: " + id));

    return getCardModel(foundCard);
  }

  /**
   * Retrieves a card by a word associated with it.
   *
   * @param word the word associated with the card to retrieve
   * @return the retrieved card model
   * @throws NotFoundCardException if the card is not found
   */
  @Override
  public CardModel getCardByWord(String word) throws NotFoundCardException {
    Card foundCard = cardRepository.findCardByWord(word).orElseThrow(
        () -> new NotFoundCardException("Not found card by word: " + word));

    return getCardModel(foundCard);
  }

  /**
   * Retrieves a paginated list of cards.
   *
   * @param offset the offset of the first card to retrieve
   * @param limit  the maximum number of cards to retrieve
   * @return a list of card models
   */
  @Override
  public List<CardModel> getCards(int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);

    Page<Card> cards = cardRepository.findAll(pageable);

    return cards.stream().map(CardServiceImpl::getCardModel).toList();
  }

  /**
   * Updates the card entity with the given details and saves it.
   *
   * @param card                   the card entity to update
   * @param uuid                   the UUID of the card deck associated with the card
   * @param word                   the word of the card
   * @param translation            the translation of the card
   * @param explanationTranslation the explanation translation of the card
   * @param explanation            the explanation of the card
   * @return the updated card model
   */
  @NotNull
  private CardModel updateCardEntity(Card card, UUID uuid, String word, String translation,
                                     String explanationTranslation, String explanation) {
    CardDeck cardDeck = cardDeckRepository.getReferenceById(uuid);

    card.setWord(word);
    card.setTranslation(translation);
    card.setCardDeck(cardDeck);
    card.setExplanationTranslation(explanationTranslation);
    card.setExplanation(explanation);

    Card savedCard = cardRepository.save(card);

    return getCardModel(savedCard);
  }

  /**
   * Converts a card entity to a card model.
   *
   * @param foundCard the card entity to convert
   * @return the converted card model
   */
  @NotNull
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
}
