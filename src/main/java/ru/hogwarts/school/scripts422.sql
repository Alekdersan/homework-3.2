CREATE TABLE cars
(
    Id    SERIAL PRIMARY KEY,
    Brand VARCHAR,
    Model VARCHAR,
    Cost  NUMERIC
);

CREATE TABLE driver
(
    Id             INTEGER PRIMARY KEY,
    Name           TEXT,
    Age            INTEGER,
    Driver_license BOOLEAN,
    car_id         INTEGER REFERENCES car (Id)
);
