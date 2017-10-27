CREATE TABLE items (
  id          SERIAL PRIMARY KEY,
  description VARCHAR(255),
  created     TIMESTAMP WITHOUT TIME ZONE,
  done        BOOLEAN DEFAULT FALSE
);