package ua.nure.englishcards.service.model;

public record NewCardModel(
    String word,
    String translation,
    String explanation,
    String explanationTranslation
) {
}
