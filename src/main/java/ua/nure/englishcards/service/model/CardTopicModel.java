package ua.nure.englishcards.service.model;

import java.util.UUID;

/**
 * A record representing a card topic model.
 * Each card topic model contains an ID and a name.
 */
public record CardTopicModel(
    UUID id,
    String name
) {
}