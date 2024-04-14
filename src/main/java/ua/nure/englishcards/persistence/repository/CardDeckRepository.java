package ua.nure.englishcards.persistence.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.englishcards.persistence.entity.CardDeck;
import ua.nure.englishcards.persistence.entity.CardTopic;

public interface CardDeckRepository extends JpaRepository<CardDeck, UUID> {

  Optional<CardDeck> findCardDeckByName(String name);

  Page<CardDeck> findCardDecksByCardTopic(CardTopic cardTopic, Pageable pageable);

  Page<CardDeck> findCardDecksByUserId(UUID userId, Pageable pageable);

}
