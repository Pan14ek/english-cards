package ua.nure.englishcards.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.englishcards.service.NotFoundUserException;
import ua.nure.englishcards.service.UserService;
import ua.nure.englishcards.service.model.NewUserModel;
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
  public ResponseEntity<UserModel> addNewUser(NewUserModel newUserModel) {
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

}
