package ua.nure.englishcards.service;

import java.util.List;
import java.util.UUID;
import ua.nure.englishcards.service.model.CardTopicModel;
import ua.nure.englishcards.service.model.NewCardTopic;

public interface CardTopicService {

  CardTopicModel saveNewTopic(NewCardTopic newCardTopic);

  CardTopicModel getTopicById(UUID id) throws NotFoundCardTopicException;

  List<CardTopicModel> getTopics(int offset, int limit);

}
