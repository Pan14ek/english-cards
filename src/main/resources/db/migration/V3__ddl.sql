CREATE TABLE if not exists users
(
    id              UUID         not null primary key,
    email           varchar(255) not null unique,
    nickname        varchar(32)  not null unique,
    password        varchar(255) not null,
    registered_date timestamp default now()
);

CREATE INDEX if not exists index_users ON users (email);

CREATE TABLE if not exists card_topics
(
    id   UUID         not null primary key,
    name varchar(255) not null
);

CREATE INDEX if not exists index_card_topics ON card_topics (name);

CREATE TABLE if not exists card_decks
(
    id            UUID         not null primary key,
    name          varchar(255) not null,
    description   varchar(255),
    user_id       UUID         not null,
    card_topic_id UUID         not null,
    FOREIGN KEY (user_id) REFERENCES users,
    FOREIGN KEY (card_topic_id) REFERENCES card_topics
);

CREATE INDEX if not exists index_card_decks ON card_decks (name);
CREATE INDEX if not exists index_card_decks_user_id ON card_decks (user_id);

CREATE TABLE if not exists cards
(
    id                      UUID         not null primary key,
    word                    varchar(255) not null,
    translation             varchar(255) not null,
    explanation             varchar(255),
    explanation_translation varchar(255),
    card_deck_id            UUID         not null,
    FOREIGN KEY (card_deck_id) REFERENCES card_decks
);

CREATE INDEX if not exists index_cards ON cards (word);
CREATE INDEX if not exists index_cards_card_deck_id ON cards (card_deck_id);