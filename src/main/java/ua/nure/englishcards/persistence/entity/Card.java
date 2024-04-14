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

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card extends EntityWithUuid {

  @Column(name = "word", nullable = false)
  private String word;

  @Column(name = "translation", nullable = false)
  private String translation;

  @Column(name = "translation")
  private String explanation;

  @Column(name = "explanation_translation")
  private String explanationTranslation;

  @ManyToOne(fetch = FetchType.LAZY)
  private CardDeck cardDeck;

}
