DROP TABLE IF EXISTS job_offers;

CREATE TABLE job_offers (
  id          SERIAL PRIMARY KEY,
  title       VARCHAR(255)   NOT NULL,
  body        VARCHAR(10000) NOT NULL,
  create_date TIMESTAMP      NOT NULL
);