package ua.nure.englishcards.service.model;

import java.util.UUID;

public record UserModel(UUID id, String email, String nickname) {
}
