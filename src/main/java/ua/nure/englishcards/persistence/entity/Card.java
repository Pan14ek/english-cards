package ua.nure.englishcards.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity representing a card.
 * Each card has a word, its translation, an optional explanation, an optional explanation
 * translation, and is associated with a card deck.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card extends EntityWithUuid {
  /**
   * The word on the card.
   */
  @Column(name = "word", nullable = false)
  private String word;

  /**
   * The translation of the word.
   */
  @Column(name = "translation", nullable = false)
  private String translation;

  /**
   * The explanation of the word.
   */
  @Column(name = "explanation")
  private String explanation;

  /**
   * The translation of the explanation.
   */
  @Column(name = "explanation_translation")
  private String explanationTranslation;

  /**
   * The card deck to which this card belongs.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  private CardDeck cardDeck;
}
