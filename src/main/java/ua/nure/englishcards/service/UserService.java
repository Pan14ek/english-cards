package ua.nure.englishcards.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.englishcards.persistence.entity.User;
import ua.nure.englishcards.persistence.repository.UserRepository;
import ua.nure.englishcards.service.model.NewUserModel;
import ua.nure.englishcards.service.model.UserModel;

/**
 * This service is responsible for handling user actions.
 * It can be adding, updating, deleting, receiving users.
 */
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * Adds a new user to the DB.
   *
   * @param user is a new user with the model {@code NewUserModel}.
   * @return saved user in DB with the model {@code UserModel}
   */
  @Transactional
  public UserModel addNewUser(NewUserModel user) {
    User newUser = new User();

    newUser.setEmail(user.email());
    newUser.setNickname(user.nickname());
    newUser.setPassword(passwordEncoder.encode(user.password()));

    User saveUser = userRepository.save(newUser);

    return new UserModel(
        saveUser.getId(),
        saveUser.getEmail(),
        saveUser.getNickname()
    );
  }

}
