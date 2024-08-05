package ua.nure.englishcards.service.model;

import java.util.UUID;

/**
 * A record representing a model for creating a new card deck.
 * Contains the name of the card deck, the ID of the user who owns the card deck,
 * the card topic, and an optional description.
 */
public record NewCardDeckModel(String name, UUID userId, CardTopicModel cardTopic,
                               String description) {
}
