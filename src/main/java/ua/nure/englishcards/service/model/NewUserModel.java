package ua.nure.englishcards.service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * This record contains fields email, nickname and password that are required for a new user.
 *
 * @param email    is user email
 * @param nickname is a nickname that should be more or equals 5 length and less than 32 length
 * @param password is a user password
 */
public record NewUserModel(
    @NotBlank
    @Email
    String email,
    @Size(min = 5, max = 32)
    @NotBlank
    String nickname,
    @Size(min = 8, max = 128)
    @NotBlank
    String password) {
}
