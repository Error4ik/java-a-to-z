-- DROP TABLE IF EXISTS comments;
-- DROP TABLE IF EXISTS files;
-- DROP TABLE IF EXISTS claim;
-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS role;
-- DROP TABLE IF EXISTS rights;
-- DROP TABLE IF EXISTS category;
-- DROP TABLE IF EXISTS claim_status;

CREATE TABLE rights (
ID SERIAL PRIMARY KEY,
rights VARCHAR (255)
);

CREATE TABLE role (
ID SERIAL PRIMARY KEY,
role VARCHAR(255),
rights_id INTEGER NOT NULL REFERENCES rights(id)
);

CREATE TABLE users (
ID SERIAL PRIMARY KEY,
user_name VARCHAR(255),
role_id INTEGER NOT NULL REFERENCES role(id)
);

CREATE TABLE category (
ID SERIAL PRIMARY KEY,
category VARCHAR (255)
);

CREATE TABLE claim_status (
ID SERIAL PRIMARY KEY,
status VARCHAR (255)
);

CREATE TABLE claim (
ID SERIAL PRIMARY KEY,
claim_name VARCHAR (255),
user_id INTEGER NOT NULL REFERENCES users(id),
create_date TIMESTAMP,
category_id INTEGER NOT NULL REFERENCES category(id),
claim_status_id INTEGER NOT NULL REFERENCES claim_status(id)
);

CREATE TABLE comments (
ID SERIAL PRIMARY KEY,
commentary TEXT,
create_date TIMESTAMP,
claime_id INTEGER NOT NULL REFERENCES claim(id)
);

CREATE TABLE files(
ID SERIAL PRIMARY KEY,
file_path VARCHAR (1000),
claime_id INTEGER NOT NULL REFERENCES claim(id)
);

INSERT INTO rights (rights) VALUES ('READ');
INSERT INTO rights (rights) VALUES ('WRITE');

INSERT INTO role (role, rights_id) VALUES ('User', 1);
INSERT INTO role (role, rights_id) VALUES ('Admin', 2);

INSERT INTO users (user_name, role_id) VALUES ('Alex', 1);
INSERT INTO users (user_name, role_id) VALUES ('Bob', 2);

INSERT INTO category (category) VALUES ('Read');
INSERT INTO category (category) VALUES ('Write');

INSERT INTO claim_status (status) VALUES ('Is pending');
INSERT INTO claim_status (status) VALUES ('Is fulfilled');
INSERT INTO claim_status (status) VALUES ('Is finished');

INSERT INTO claim (claim_name, user_id, create_date, category_id, claim_status_id)
VALUES ('Search text in document', 1, NOW(), 1, 1);
INSERT INTO claim (claim_name, user_id, create_date, category_id, claim_status_id)
VALUES ('Change text to document', 2, NOW(), 2, 1);

INSERT INTO comments (commentary, create_date, claime_id)
VALUES ('It is necessary to count how many times a word Hello appears in the document', NOW(), 1);
INSERT INTO comments (commentary, create_date, claime_id)
VALUES ('Replace all colons in the document with a semicolon.', NOW(), 1);

INSERT INTO files (file_path, claime_id) VALUES ('upload/file/1', 1);
INSERT INTO files (file_path, claime_id) VALUES ('upload/file/2', 2);
