package ua.nure.englishcards.api;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.englishcards.service.NotFoundUserException;
import ua.nure.englishcards.service.UserService;
import ua.nure.englishcards.service.model.NewUserModel;
import ua.nure.englishcards.service.model.UpdateUserModel;
import ua.nure.englishcards.service.model.UserModel;

/**
 * This class is responsible for the CRUD operation on the {@code User} model.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  /**
   * Saves a new user to the english card system.
   *
   * @param newUserModel is a new user
   * @return saved user
   */
  @PostMapping
  public ResponseEntity<UserModel> addNewUser(@RequestBody NewUserModel newUserModel) {
    UserModel userModel = userService.addNewUser(newUserModel);

    return ResponseEntity.ok(userModel);
  }

  /**
   * Receives a user by uuid If user was not found
   * the endpoint throws the exception with status code Not found.
   *
   * @param uuid is user id
   * @return {@code UserModel} or {@code ResponseStatusException}
   */
  @GetMapping("/{uuid}")
  public ResponseEntity<UserModel> getUserByUuid(@PathVariable("uuid") String uuid) {
    try {
      UserModel userModel = userService.getUserByUuid(uuid);
      return ResponseEntity.ok(userModel);
    } catch (NotFoundUserException notFoundUserException) {

      throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundUserException.getMessage());
    }
  }

  /**
   * Receives a user by email if user was not found the endpoint throws
   * the exception with status code Not found.
   *
   * @param email is user email
   * @return {@code UserModel} or {@code ResponseStatusException}
   */
  @GetMapping
  public ResponseEntity<UserModel> getUserByEmail(@RequestParam("email") String email) {
    try {
      UserModel userModel = userService.getUserByEmail(email);
      return ResponseEntity.ok(userModel);
    } catch (NotFoundUserException notFoundUserException) {

      throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundUserException.getMessage());
    }
  }

  /**
   * Updates email, nickname for users.
   *
   * @param updateUserModel is updated data for the user model
   * @return {@code UserModel} or {@code ResponseStatusException}
   */
  @PutMapping
  public ResponseEntity<UserModel> updateUser(@RequestBody UpdateUserModel updateUserModel) {
    try {
      UserModel user =
          new UserModel(UUID.fromString(updateUserModel.id()), updateUserModel.email(),
              updateUserModel.nickname());

      UserModel userModel = userService.updateUser(user);
      return ResponseEntity.ok(userModel);
    } catch (NotFoundUserException notFoundUserException) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundUserException.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<List<UserModel>> getAllUsersByOffsetAndLimit(
      @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
    return ResponseEntity.ok(userService.getUsers(offset, limit));
  }

}
