package ua.nure.englishcards.service.model;

/**
 * This record contains uuid, email and nickname fields that are needed for updating user.
 *
 * @param id       is a user id
 * @param email    is a email id
 * @param nickname is a user nickname
 */
public record UpdateUserModel(String id, String email, String nickname) {
}
