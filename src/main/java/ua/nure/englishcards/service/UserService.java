package ua.nure.englishcards.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.englishcards.persistence.entity.User;
import ua.nure.englishcards.persistence.repository.UserRepository;
import ua.nure.englishcards.service.model.NewUserModel;
import ua.nure.englishcards.service.model.UserModel;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

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
