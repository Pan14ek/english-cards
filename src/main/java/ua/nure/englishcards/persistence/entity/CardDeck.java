package ua.nure.englishcards.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity representing a card deck.
 * Each card deck has a name, an optional description, and is associated with a user and a card topic.
 * A card deck contains multiple cards.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_decks")
public class CardDeck extends EntityWithUuid {

  /**
   * The name of the card deck.
   */
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * The description of the card deck.
   */
  @Column(name = "description")
  private String description;

  /**
   * The user who owns the card deck.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  /**
   * The card topic to which this card deck belongs.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  private CardTopic cardTopic;

  /**
   * The list of cards in this card deck.
   */
  @OneToMany(mappedBy = "cardDeck", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Card> cards = new ArrayList<>();

  /**
   * Adds a card to this card deck.
   *
   * @param card the card to add
   */
  public void addCard(Card card) {
    cards.add(card);
    card.setCardDeck(this);
  }

  /**
   * Removes a card from this card deck.
   *
   * @param card the card to remove
   */
  public void deleteCard(Card card) {
    cards.remove(card);
    card.setCardDeck(null);
  }
}
