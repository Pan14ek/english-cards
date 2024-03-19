package ua.nure.englishcards.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The entity represents the table users in DB
 * that contains fields id, email, nickname and password.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends EntityWithUuid {

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "nickname", nullable = false)
  private String nickname;

  @Column(name = "password", nullable = false)
  private String password;

}
