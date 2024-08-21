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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.englishcards.service.CardTopicService;
import ua.nure.englishcards.service.NotFoundCardTopicException;
import ua.nure.englishcards.service.model.CardTopicModel;
import ua.nure.englishcards.service.model.NewCardTopicModel;

/**
 * REST controller for managing card topics.
 * Provides endpoints for retrieving, adding, and searching card topics.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/cardtopic")
public class CardTopicController {

  private final CardTopicService cardTopicService;

  /**
   * Retrieves a paginated list of card topics.
   *
   * @param offset the offset of the first card topic to retrieve
   * @param limit  the maximum number of card topics to retrieve
   * @return a ResponseEntity containing the list of card topics
   */
  @Timed
  @Counted
  @GetMapping("/all")
  public ResponseEntity<List<CardTopicModel>> getCardTopics(@RequestParam("offset") int offset,
                                                            @RequestParam("limit") int limit) {
    List<CardTopicModel> cardTopics = cardTopicService.getTopics(offset, limit);

    return ResponseEntity.ok(cardTopics);
  }

  /**
   * Retrieves a card topic by its UUID.
   *
   * @param uuid the UUID of the card topic to retrieve
   * @return a ResponseEntity containing the retrieved card topic
   * @throws ResponseStatusException if the card topic is not found
   */
  @Timed
  @Counted
  @GetMapping("/{uuid}")
  public ResponseEntity<CardTopicModel> getCardTopicByUuid(@PathVariable("uuid") String uuid) {
    try {
      CardTopicModel foundCardTopic = cardTopicService.getTopicById(UUID.fromString(uuid));
      return ResponseEntity.ok(foundCardTopic);
    } catch (NotFoundCardTopicException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  /**
   * Adds a new card topic.
   *
   * @param cardTopic the model containing the details of the new card topic
   * @return a ResponseEntity containing the created card topic
   */
  @Timed
  @Counted
  @PostMapping
  public ResponseEntity<CardTopicModel> addCardTopic(NewCardTopicModel cardTopic) {
    CardTopicModel newCardTopic = cardTopicService.saveNewTopic(cardTopic);

    return ResponseEntity.ok(newCardTopic);
  }

}
