package ua.nure.englishcards.api;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.englishcards.service.CardService;
import ua.nure.englishcards.service.NotFoundCardException;
import ua.nure.englishcards.service.model.CardModel;
import ua.nure.englishcards.service.model.NewCardModel;

/**
 * REST controller for managing cards.
 * Provides endpoints for retrieving, adding, updating, and searching cards.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardController {

  private final CardService cardService;

  /**
   * Retrieves a paginated list of cards.
   *
   * @param offset the offset of the first card to retrieve
   * @param limit  the maximum number of cards to retrieve
   * @return a ResponseEntity containing the list of cards
   */
  @Timed
  @Counted
  @GetMapping("/all")
  public ResponseEntity<List<CardModel>> getCards(@RequestParam("offset") int offset,
                                                  @RequestParam("limit") int limit) {
    List<CardModel> cards = cardService.getCards(offset, limit);

    return ResponseEntity.ok(cards);
  }

  /**
   * Adds a new card.
   *
   * @param newCardModel the model containing the details of the new card
   * @return a ResponseEntity containing the created card
   */
  @Timed
  @Counted
  @PostMapping
  public ResponseEntity<CardModel> addCard(NewCardModel newCardModel) {
    CardModel cardModel = cardService.saveNewCard(newCardModel);

    return ResponseEntity.ok(cardModel);
  }

  /**
   * Updates an existing card.
   *
   * @param cardModel the model containing the updated details of the card
   * @return a ResponseEntity containing the updated card
   * @throws ResponseStatusException if the card is not found
   */
  @Timed
  @Counted
  @PutMapping
  public ResponseEntity<CardModel> updateCard(CardModel cardModel) {
    try {
      CardModel updatedCard = cardService.updateCard(cardModel);

      return ResponseEntity.ok(updatedCard);
    } catch (NotFoundCardException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  /**
   * Retrieves a card by its UUID.
   *
   * @param uuid the UUID of the card to retrieve
   * @return a ResponseEntity containing the retrieved card
   * @throws ResponseStatusException if the card is not found
   */
  @Timed
  @Counted
  @GetMapping("/{uuid}")
  public ResponseEntity<CardModel> getCardByUuid(@PathVariable("uuid") String uuid) {
    try {
      CardModel cardModel = cardService.getCardById(UUID.fromString(uuid));

      return ResponseEntity.ok(cardModel);
    } catch (NotFoundCardException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  /**
   * Retrieves a card by a word associated with it.
   *
   * @param word the word associated with the card to retrieve
   * @return a ResponseEntity containing the retrieved card
   * @throws ResponseStatusException if the card is not found
   */
  @Timed
  @Counted
  @GetMapping("/word/{word}")
  public ResponseEntity<CardModel> getCardByWord(@PathVariable("word") String word) {
    try {
      CardModel cardModel = cardService.getCardByWord(word);

      return ResponseEntity.ok(cardModel);
    } catch (NotFoundCardException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

}
