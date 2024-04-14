package ua.nure.englishcards.service;

import java.util.List;
import java.util.UUID;
import ua.nure.englishcards.service.model.CardModel;
import ua.nure.englishcards.service.model.NewCardModel;

public interface CardService {

  CardModel saveNewCard(NewCardModel newCardModel);

  CardModel updateCard(CardModel cardModel) throws NotFoundCardException;

  CardModel getCardById(UUID id) throws NotFoundCardException;

  CardModel getCardByWord(String word) throws NotFoundCardException;

  List<CardModel> getCards(int offset, int limit);

}
