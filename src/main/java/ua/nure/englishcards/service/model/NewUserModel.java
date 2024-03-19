package ua.nure.englishcards.service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
