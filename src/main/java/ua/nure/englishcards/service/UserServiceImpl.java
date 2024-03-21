package ua.nure.englishcards.service;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.englishcards.persistence.entity.User;
import ua.nure.englishcards.persistence.repository.UserRepository;
import ua.nure.englishcards.service.model.NewUserModel;
import ua.nure.englishcards.service.model.UserModel;

/**
 * This class is implementation of the {@code UserService} service
 * that is responsible for user model.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * Adds a new user to the system.
   *
   * @param user is a new user with the model {@code NewUserModel}.
   * @return saved user in DB with the model {@code UserModel}
   */
  @Transactional
  @Override
  public UserModel addNewUser(@NotNull NewUserModel user) {
    User newUser = new User();

    newUser.setEmail(user.email());
    newUser.setNickname(user.nickname());
    newUser.setPassword(passwordEncoder.encode(user.password()));
    newUser.setRegisteredDate(LocalDateTime.now());

    User saveUser = userRepository.save(newUser);

    return getUserModel(saveUser);
  }

  /**
   * Receives user by uuid or throws exception {@code NotFoundUserException} if user is not found.
   *
   * @param uuid is UUID of user
   * @return {@code UserModel}
   * @throws NotFoundUserException if user is not found
   */
  @Override
  public UserModel getUserByUuid(@NotNull String uuid) throws NotFoundUserException {
    Optional<User> foundUser = userRepository.findById(UUID.fromString(uuid));

    if (foundUser.isEmpty()) {
      throw new NotFoundUserException(String.format("Not found user by uuid %s", uuid));
    }

    User user = foundUser.get();

    return getUserModel(user);
  }

  /**
   * Updates the user in the system or
   * throws exception {@code NotFoundUserException} if user is not found.
   *
   * @param user is an updated user
   * @return {@code UserModel} that was updated in the system
   * @throws NotFoundUserException if user is not found
   */
  @Transactional
  @Override
  public UserModel updateUser(UserModel user) throws NotFoundUserException {
    Optional<User> foundUser = userRepository.findById(user.id());

    if (foundUser.isEmpty()) {
      throw new NotFoundUserException(String.format("Not found user by uuid %s", user.id()));
    }

    User userFromDb = foundUser.get();

    userFromDb.setEmail(user.email());
    userFromDb.setNickname(user.nickname());

    User updatedUser = userRepository.save(userFromDb);

    return getUserModel(updatedUser);
  }

  /**
   * Receives user by email or throws exception {@code NotFoundUserException} if user is not found.
   *
   * @param email is a user email
   * @return {@code UserModel}
   * @throws NotFoundUserException if user is not found
   */
  @Override
  public UserModel getUserByEmail(@NotNull String email) throws NotFoundUserException {
    Optional<User> foundUser = userRepository.findByEmail(email);

    if (foundUser.isEmpty()) {
      throw new NotFoundUserException(String.format("Not found user by email %s", email));
    }

    User user = foundUser.get();

    return getUserModel(user);
  }

  /**
   * Receives users using offset and limit to use pagination.
   *
   * @param offset is a step of getting users
   * @param limit  is a limitation
   * @return a list of users
   */
  @Override
  public List<UserModel> getUsers(int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);

    Page<User> allUsers = userRepository.findAll(pageable);

    return allUsers.stream().map(UserServiceImpl::getUserModel).toList();
  }

  private static UserModel getUserModel(User user) {
    return new UserModel(
        user.getId(),
        user.getEmail(),
        user.getNickname()
    );
  }

}
