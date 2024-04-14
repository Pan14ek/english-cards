package ua.nure.englishcards.service.model;

import java.util.UUID;

public record NewCardDeckModel(String name, UUID userId, CardTopicModel cardTopic,
                               String description) {
}
