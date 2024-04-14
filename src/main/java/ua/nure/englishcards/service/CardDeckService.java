package ua.nure.englishcards.service;

import java.util.List;
import java.util.UUID;
import ua.nure.englishcards.service.model.CardDeckModel;
import ua.nure.englishcards.service.model.CardTopicModel;
import ua.nure.englishcards.service.model.NewCardDeckModel;
import ua.nure.englishcards.service.model.UserModel;

public interface CardDeckService {

  CardDeckModel createNewCardDeck(NewCardDeckModel newCardDeckModel);

  CardDeckModel getCardDeckByUuid(UUID id) throws NotFoundCardDeckException;

  CardDeckModel getCardDeckByName(String name) throws NotFoundCardDeckException;

  List<CardDeckModel> getCardDecksByTopic(CardTopicModel cardTopic, int offset, int limit);

  List<CardDeckModel> getCardDeckByUser(UserModel user, int offset, int limit);

  List<CardDeckModel> getCardDecks(int offset, int limit);

}
