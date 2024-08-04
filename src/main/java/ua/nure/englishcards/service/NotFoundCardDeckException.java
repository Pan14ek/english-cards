package ua.nure.englishcards.service;

/**
 * Exception thrown when a card deck is not found.
 */
public class NotFoundCardDeckException extends Exception {

  /**
   * Constructs a new NotFoundCardDeckException with the specified detail message.
   *
   * @param message the detail message
   */
  public NotFoundCardDeckException(String message) {
    super(message);
  }
}
