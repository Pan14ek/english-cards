package ua.nure.englishcards.service.model;

import java.util.UUID;

/**
 * This record contains fields that are required for registered user.
 *
 * @param id       is a user id
 * @param email    is a email id
 * @param nickname is a user nickname
 */
public record UserModel(
    UUID id,
    String email,
    String nickname
) {
}
