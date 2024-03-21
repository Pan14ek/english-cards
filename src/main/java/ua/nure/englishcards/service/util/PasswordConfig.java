package ua.nure.englishcards.service.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class contains configuration methods that receive beans.
 */
@Configuration
public class PasswordConfig {

  /**
   * Receives the PasswordEncoder object.
   *
   * @return the password encoder
   */
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

}
