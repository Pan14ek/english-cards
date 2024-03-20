package ua.nure.englishcards.service;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import ua.nure.englishcards.service.model.NewUserModel;
import ua.nure.englishcards.service.model.UserModel;

/**
 * This service is responsible for handling user actions.
 * It can be adding, updating, deleting, receiving users.
 */
public interface UserService {

  /**
   * Adds a new user to the system.
   *
   * @param user is a new user with the model {@code NewUserModel}.
   * @return saved user in DB with the model {@code UserModel}
   */
  UserModel addNewUser(@NotNull NewUserModel user);

  /**
   * Receives user by uuid or throws exception {@code NotFoundUserException} if user is not found.
   *
   * @param uuid is UUID of user
   * @return {@code UserModel}
   * @throws NotFoundUserException if user is not found
   */
  UserModel getUserByUuid(@NotNull String uuid) throws NotFoundUserException;

  /**
   * Updates the user in the system or
   * throws exception {@code NotFoundUserException} if user is not found.
   *
   * @param user is an updated user
   * @return {@code UserModel} that was updated in the system
   * @throws NotFoundUserException if user is not found
   */
  UserModel updateUser(@NotNull UserModel user) throws NotFoundUserException;

  /**
   * Receives user by email or throws exception {@code NotFoundUserException} if user is not found.
   *
   * @param email is a user email
   * @return {@code UserModel}
   * @throws NotFoundUserException if user is not found
   */
  UserModel getUserByEmail(@NotNull String email) throws NotFoundUserException;

  /**
   * Receives users using offset and limit to use pagination.
   *
   * @param offset is a step of getting users
   * @param limit  is a limitation
   * @return a list of users
   */
  List<UserModel> getUsers(int offset, int limit);
}
