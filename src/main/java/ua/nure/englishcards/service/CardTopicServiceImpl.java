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
import ua.nure.englishcards.service.model.NewCardTopic;

@Service
@RequiredArgsConstructor
public class CardTopicServiceImpl implements CardTopicService {

  private final CardTopicRepository topicRepository;

  @Transactional
  @Override
  public CardTopicModel saveNewTopic(NewCardTopic newCardTopic) {
    CardTopic cardTopic = new CardTopic();

    cardTopic.setName(newCardTopic.name());

    CardTopic savedCardTopic = topicRepository.save(cardTopic);

    return getCardTopicModel(savedCardTopic);
  }

  @Override
  public CardTopicModel getTopicById(UUID id) throws NotFoundCardTopicException {
    CardTopic foundCardTopic = topicRepository.findById(id)
        .orElseThrow(() -> new NotFoundCardTopicException(id.toString()));

    return getCardTopicModel(foundCardTopic);
  }

  @Override
  public List<CardTopicModel> getTopics(int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);

    Page<CardTopic> topics = topicRepository.findAll(pageable);

    return topics.stream().map(CardTopicServiceImpl::getCardTopicModel).toList();
  }

  private static CardTopicModel getCardTopicModel(CardTopic foundCardTopic) {
    return new CardTopicModel(foundCardTopic.getId(), foundCardTopic.getName());
  }

}
