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
import ua.nure.englishcards.persistence.repository.CardDeckRepository;
import ua.nure.englishcards.persistence.repository.CardRepository;
import ua.nure.englishcards.service.model.CardModel;
import ua.nure.englishcards.service.model.NewCardModel;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;
  private final CardDeckRepository cardDeckRepository;

  @Transactional
  @Override
  public CardModel saveNewCard(NewCardModel newCardModel) {
    Card card = new Card();

    CardDeck cardDeck = cardDeckRepository.getReferenceById(newCardModel.cardDeckId());

    card.setWord(newCardModel.word());
    card.setTranslation(newCardModel.translation());
    card.setCardDeck(cardDeck);
    card.setExplanationTranslation(newCardModel.explanationTranslation());
    card.setExplanation(newCardModel.explanation());

    Card savedCard = cardRepository.save(card);

    return getCardModel(savedCard);
  }

  @Transactional
  @Override
  public CardModel updateCard(CardModel cardModel) throws NotFoundCardException {
    Card foundCard = cardRepository.findById(cardModel.id()).orElseThrow(
        () -> new NotFoundCardException("Not found card by id: " + cardModel.id()));

    CardDeck cardDeck = cardDeckRepository.getReferenceById(cardModel.cardDeckId());

    foundCard.setWord(cardModel.word());
    foundCard.setTranslation(cardModel.translation());
    foundCard.setCardDeck(cardDeck);
    foundCard.setExplanationTranslation(cardModel.explanationTranslation());
    foundCard.setExplanation(cardModel.explanation());

    Card updatedCard = cardRepository.save(foundCard);

    return getCardModel(updatedCard);
  }

  @Override
  public CardModel getCardById(UUID id) throws NotFoundCardException {
    Card foundCard = cardRepository.findById(id).orElseThrow(
        () -> new NotFoundCardException("Not found card by id: " + id));

    return getCardModel(foundCard);
  }

  @Override
  public CardModel getCardByWord(String word) throws NotFoundCardException {
    Card foundCard = cardRepository.findCardByWord(word).orElseThrow(
        () -> new NotFoundCardException("Not found card by word: " + word));

    return getCardModel(foundCard);
  }

  @Override
  public List<CardModel> getCards(int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);

    Page<Card> cards = cardRepository.findAll(pageable);

    return cards.stream().map(CardServiceImpl::getCardModel).toList();
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
}
