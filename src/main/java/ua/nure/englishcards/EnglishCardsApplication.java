package ua.nure.englishcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The main class that starts application.
 */
@SpringBootApplication
@EnableTransactionManagement
public class EnglishCardsApplication {

  public static void main(String[] args) {
    SpringApplication.run(EnglishCardsApplication.class, args);
  }

}
