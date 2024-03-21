package ua.nure.englishcards.service.model;

import java.util.UUID;

public record CardModel(
    UUID id,
    String word,
    String translation,
    String explanation,
    String explanationTranslation
) {
}
