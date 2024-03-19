package ua.nure.englishcards.persistence.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;
import lombok.Data;

/**
 * This entity represents a super class with uuid.
 */
@Data
@MappedSuperclass
public class EntityWithUuid {

  @Id
  private UUID id;

  public EntityWithUuid() {
    this.id = UUID.randomUUID();
  }

}
