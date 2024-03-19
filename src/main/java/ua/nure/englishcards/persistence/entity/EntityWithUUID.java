package ua.nure.englishcards.persistence.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;
import lombok.Data;

@Data
@MappedSuperclass
public class EntityWithUUID {

  @Id
  private UUID id;

  public EntityWithUUID() {
    this.id = UUID.randomUUID();
  }

}