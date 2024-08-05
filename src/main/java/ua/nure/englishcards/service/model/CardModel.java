package ua.nure.englishcards.service.model;

import java.util.UUID;

/**
 * A record representing a card model.
 * Each card model contains an ID, word, translation, explanation, explanation translation, and the ID of the associated card deck.
 */
public record CardModel(
    UUID id,
    String word,
    String translation,
    String explanation,
    String explanationTranslation,
    UUID cardDeckId
) {
}
