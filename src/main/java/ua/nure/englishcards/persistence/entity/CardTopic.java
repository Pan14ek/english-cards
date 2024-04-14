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

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_topics")
public class CardTopic extends EntityWithUuid {

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "cardTopic", cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<CardDeck> cardDecks = new ArrayList<>();

  public void addCardDeck(CardDeck cardDeck) {
    cardDecks.add(cardDeck);
    cardDeck.setCardTopic(this);
  }

  public void removeCardDeck(CardDeck cardDeck) {
    cardDecks.remove(cardDeck);
    cardDeck.setCardTopic(null);
  }

}
