package ua.nure.englishcards.service;

/**
 * This exception should throw if user was not found from the service layer.
 */
public class NotFoundUserException extends Exception {

  /**
   * Creates a new constructor with the message parameter.
   *
   * @param message is an error message
   */
  public NotFoundUserException(String message) {
    super(message);
  }
}
