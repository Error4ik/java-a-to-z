DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS task;

CREATE TABLE task (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(255),
  description VARCHAR(255),
  create_date TIMESTAMP
);
CREATE TABLE comments (
  id          SERIAL PRIMARY KEY,
  task_id     INTEGER NOT NULL REFERENCES task(id),
  create_date TIMESTAMP,
  comment     VARCHAR(255)
);
