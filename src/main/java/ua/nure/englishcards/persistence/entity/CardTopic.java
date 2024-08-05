package ua.nure.englishcards.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity representing a card topic.
 * Each card topic has a name and is associated with multiple card decks.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_topics")
public class CardTopic extends EntityWithUuid {

  /**
   * The name of the card topic.
   */
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * The list of card decks associated with this card topic.
   */
  @OneToMany(mappedBy = "cardTopic", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CardDeck> cardDecks = new ArrayList<>();

  /**
   * Adds a card deck to this card topic.
   *
   * @param cardDeck the card deck to add
   */
  public void addCardDeck(CardDeck cardDeck) {
    cardDecks.add(cardDeck);
    cardDeck.setCardTopic(this);
  }

  /**
   * Removes a card deck from this card topic.
   *
   * @param cardDeck the card deck to remove
   */
  public void removeCardDeck(CardDeck cardDeck) {
    cardDecks.remove(cardDeck);
    cardDeck.setCardTopic(null);
  }
}
