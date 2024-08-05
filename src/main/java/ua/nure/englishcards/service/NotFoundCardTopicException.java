package ua.nure.englishcards.service;

/**
 * Exception thrown when a card topic is not found.
 */
public class NotFoundCardTopicException extends Exception {

  /**
   * Constructs a new NotFoundCardTopicException with the specified detail message.
   *
   * @param message the detail message
   */
  public NotFoundCardTopicException(String message) {
    super(message);
  }
}
