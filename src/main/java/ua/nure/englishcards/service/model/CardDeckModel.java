package ua.nure.englishcards.service.model;

import java.util.List;
import java.util.UUID;

public record CardDeckModel(
    UUID id,
    String name,
    String description,
    List<CardModel> cards,
    CardTopicModel topic,
    UserModel user
) {
}
