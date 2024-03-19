package ua.nure.englishcards.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
