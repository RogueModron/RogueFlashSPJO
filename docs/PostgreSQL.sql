CREATE TABLE decks
(
  id serial NOT NULL,
  version integer NOT NULL,
  description text NOT NULL,
  notes text NOT NULL,
  CONSTRAINT decks_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE decks
  OWNER TO admin;


CREATE TABLE cards
(
  id serial NOT NULL,
  version integer NOT NULL,
  deck_id integer NOT NULL,
  side_a text NOT NULL,
  side_b text NOT NULL,
  notes text NOT NULL,
  tags text NOT NULL,
  CONSTRAINT cards_pkey PRIMARY KEY (id),
  CONSTRAINT cards_decks_fk FOREIGN KEY (deck_id)
      REFERENCES decks (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cards
  OWNER TO admin;

CREATE INDEX fki_cards_decks_fk
  ON cards
  USING btree
  (deck_id);


CREATE TABLE cards_instances
(
  id serial NOT NULL,
  card_id integer NOT NULL,
  side_a_to_b boolean NOT NULL,
  disabled boolean NOT NULL,
  CONSTRAINT cards_instances_pkey PRIMARY KEY (id),
  CONSTRAINT cards_instances_cards_fk FOREIGN KEY (card_id)
      REFERENCES cards (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT card_instances_card_side_u UNIQUE (card_id, side_a_to_b)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cards_instances
  OWNER TO admin;

CREATE INDEX fki_cards_instances_cards_fk
  ON cards_instances
  USING btree
  (card_id);


CREATE TABLE cards_reviews
(
  id serial NOT NULL,
  card_instance_id integer NOT NULL,
  value smallint NOT NULL,
  date_time timestamp with time zone NULL,
  CONSTRAINT cards_reviews_pkey PRIMARY KEY (id),
  CONSTRAINT cards_reviews_cards_instances_fk FOREIGN KEY (card_instance_id)
      REFERENCES cards_instances (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cards_reviews
  OWNER TO admin;

CREATE INDEX fki_cards_reviews_cards_instances_fk
  ON cards_reviews
  USING btree
  (card_instance_id);


CREATE TABLE cards_plans
(
  id serial NOT NULL,
  card_instance_id integer NOT NULL,
  next_date timestamp with time zone NULL,
  next_days integer NOT NULL,
  last_date timestamp with time zone NULL,
  last_days integer NOT NULL,
  CONSTRAINT cards_plans_pkey PRIMARY KEY (id),
  CONSTRAINT cards_plans_cards_instances_fk FOREIGN KEY (card_instance_id)
      REFERENCES cards_instances (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cards_plans
  OWNER TO admin;

CREATE INDEX fki_cards_plans_cards_instances_fk
  ON cards_plans
  USING btree
  (card_instance_id);


CREATE TABLE current_reviews
(
  id serial NOT NULL,
  deck_id integer NOT NULL,
  card_instance_id integer NOT NULL,
  last_date_time timestamp with time zone NULL,
  CONSTRAINT current_reviews_pkey PRIMARY KEY (id),
  CONSTRAINT current_reviews_decks_fk FOREIGN KEY (deck_id)
      REFERENCES decks (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT current_reviews_cards_instances_fk FOREIGN KEY (card_instance_id)
      REFERENCES cards_instances (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE current_reviews
  OWNER TO admin;

