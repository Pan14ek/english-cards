package ua.nure.englishcards.service.model;

import java.util.UUID;

public record NewCardModel(
    String word,
    String translation,
    String explanation,
    String explanationTranslation,
    UUID cardDeckId
) {
}
