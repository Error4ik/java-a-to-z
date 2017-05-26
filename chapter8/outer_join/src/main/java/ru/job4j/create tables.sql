--DROP TABLE IF EXISTS car_body;
--DROP TABLE IF EXISTS engine;
--DROP TABLE IF EXISTS transmition;
--DROP TABLE IF EXISTS car;

CREATE TABLE car_body (
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE engine (
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE transmition (
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE car (
id SERIAL PRIMARY KEY,
name VARCHAR(255),
car_body_id INTEGER REFERENCES car_body(id),
engine_id INTEGER REFERENCES engine(id),
transmition_id INTEGER REFERENCES transmition(id)
);

INSERT INTO car_body (name) VALUES ('Sedan');
INSERT INTO car_body (name) VALUES ('Coupe');
INSERT INTO car_body (name) VALUES ('Universal');

INSERT INTO engine (name) VALUES ('V6');
INSERT INTO engine (name) VALUES ('V8');
INSERT INTO engine (name) VALUES ('V12');

INSERT INTO transmition (name) VALUES ('Manual');
INSERT INTO transmition (name) VALUES ('Automatic');
INSERT INTO transmition (name) VALUES ('Variator');

INSERT INTO car (name, car_body_id, engine_id, transmition_id) 
VALUES ('Mercedes', 2, 2, 3);

INSERT INTO car (name, car_body_id, engine_id, transmition_id)
VALUES ('BMW', 1, 1, 2);