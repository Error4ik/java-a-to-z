--all car.
SELECT car.id, car.name AS car, car_body.name AS body, engine.name AS engine, transmition.name AS transmition FROM car LEFT JOIN car_body ON 
(car_body.id = car.car_body_id) LEFT JOIN engine ON (engine.id = car.engine_id) LEFT JOIN transmition ON (transmition.id = car.transmition_id);

--all unused car_body.
SELECT car_body.name as unused_body FROM car_body LEFT JOIN car ON (car_body.id = car.car_body_id) WHERE car_body_id IS NULL;

--all unused engine.
SELECT engine.name as unused_engine FROM engine LEFT JOIN car ON (engine.id = car.engine_id) WHERE engine_id IS NULL;

--all unused transmition.
SELECT transmition.name as unused_transmition FROM transmition LEFT JOIN car ON (transmition.id = car.transmition_id) WHERE transmition_id IS NULL;
