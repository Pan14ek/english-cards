package ua.nure.englishcards.service.model;

import java.util.UUID;

/**
 * A record representing a model for creating a new card.
 * Contains the word, translation, explanation, explanation translation, and the ID of the associated card deck.
 */
public record NewCardModel(
    String word,
    String translation,
    String explanation,
    String explanationTranslation,
    UUID cardDeckId
) {
}
