package ua.nure.englishcards.service.model;

import java.util.List;
import java.util.UUID;

/**
 * A record representing a card deck model.
 * Each card deck model contains an ID, name, description, a list of cards, a topic, and a user.
 */
public record CardDeckModel(
    UUID id,
    String name,
    String description,
    List<CardModel> cards,
    CardTopicModel topic,
    UserModel user
) {
}
