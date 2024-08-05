package ua.nure.englishcards.service;

/**
 * Exception thrown when a card is not found.
 */
public class NotFoundCardException extends Exception {

  /**
   * Constructs a new NotFoundCardException with the specified detail message.
   *
   * @param message the detail message
   */
  public NotFoundCardException(String message) {
    super(message);
  }
}
