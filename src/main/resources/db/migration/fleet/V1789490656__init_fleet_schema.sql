CREATE SCHEMA IF NOT EXISTS fleet;

CREATE TYPE fleet.transmission_type AS ENUM ('MANUAL', 'AUTOMATIC', 'ROBOTIC', 'CVT');
CREATE TYPE fleet.car_status AS ENUM ('AVAILABLE', 'RENTED', 'MAINTENANCE', 'INACTIVE');
CREATE TYPE fleet.car_class AS ENUM ('ECONOMY', 'STANDARD', 'BUSINESS', 'PREMIUM', 'SPORT');
CREATE TYPE fleet.car_body_type AS ENUM (
    'SEDAN', 'HATCHBACK', 'STATION_WAGON', 'SUV', 'CROSSOVER', 'MINIVAN', 'COUPE', 'CONVERTIBLE', 'PICKUP'
);
CREATE TYPE fleet.standard_feature_type AS ENUM ('AIR_CONDITIONING', 'GPS_NAVIGATION', 'BLUETOOTH', 'CRUISE_CONTROL');
CREATE TYPE fleet.drive_type AS ENUM ('AWD', 'FWD', 'RWD');
CREATE TYPE fleet.fuel_type AS ENUM ('GASOLINE', 'DIESEL', 'LPG', 'CNG');
CREATE TYPE fleet.powertrain_type AS ENUM ('ELECTRIC', 'HYBRID', 'INTERNAL_COMBUSTION');

CREATE TABLE fleet.location
(
    id       UUID PRIMARY KEY,
    city     VARCHAR(100) NOT NULL,
    street   VARCHAR(150) NOT NULL,
    building VARCHAR(20)  NOT NULL
);

CREATE TABLE fleet.car_model
(
    id                UUID PRIMARY KEY,
    brand             VARCHAR(100)            NOT NULL,
    model             VARCHAR(100)            NOT NULL,
    year              SMALLINT                NOT NULL,
    seat_count        SMALLINT                NOT NULL CHECK (seat_count > 0),
    car_class         fleet.car_class         NOT NULL,
    body_type         fleet.car_body_type     NOT NULL,
    transmission_type fleet.transmission_type NOT NULL,
    description       TEXT,
    custom_features   JSONB                   NOT NULL DEFAULT '[]'::jsonb,

    CONSTRAINT chk_custom_features_is_array CHECK (jsonb_typeof(custom_features) = 'array')
);

CREATE TABLE fleet.car
(
    id                        UUID PRIMARY KEY,
    vin                       VARCHAR(17)      NOT NULL UNIQUE,
    model_id                  UUID             NOT NULL REFERENCES fleet.car_model (id),
    location_id               UUID             NOT NULL REFERENCES fleet.location (id),
    mileage                   BIGINT           NOT NULL CHECK (mileage >= 0),
    status                    fleet.car_status NOT NULL,
    daily_rental_price_amount NUMERIC(12, 2)   NOT NULL CHECK (daily_rental_price_amount >= 0),
    license_plate             VARCHAR(8)       NOT NULL UNIQUE
);

CREATE TABLE fleet.car_model_photo
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    car_model_id UUID     NOT NULL REFERENCES fleet.car_model (id) ON DELETE CASCADE,
    uri          TEXT     NOT NULL,
    is_primary   BOOLEAN  NOT NULL DEFAULT FALSE,
    order_index  SMALLINT NOT NULL CHECK (order_index >= 0)
);

CREATE TABLE fleet.car_model_standard_feature
(
    car_model_id UUID                        NOT NULL REFERENCES fleet.car_model (id) ON DELETE CASCADE,
    type         fleet.standard_feature_type NOT NULL,

    CONSTRAINT pk_car_model_standard_feature PRIMARY KEY (car_model_id, type)
);

CREATE TABLE fleet.powertrain_specification
(
    id              UUID PRIMARY KEY,
    car_model_id    UUID                  NOT NULL UNIQUE REFERENCES fleet.car_model (id) ON DELETE CASCADE,
    power           INT                   NOT NULL CHECK (power > 0),
    drive_type      fleet.drive_type      NOT NULL,
    powertrain_type fleet.powertrain_type NOT NULL
);

CREATE TABLE fleet.electric_powertrain
(
    powertrain_id    UUID PRIMARY KEY REFERENCES fleet.powertrain_specification (id) ON DELETE CASCADE,
    range            INT NOT NULL CHECK (range > 0),
    battery_capacity INT NOT NULL CHECK (battery_capacity > 0)
);

CREATE TABLE fleet.hybrid_powertrain
(
    powertrain_id    UUID PRIMARY KEY REFERENCES fleet.powertrain_specification (id) ON DELETE CASCADE,
    range            INT             NOT NULL CHECK (range > 0),
    fuel_type        fleet.fuel_type NOT NULL,
    battery_capacity INT             NOT NULL CHECK (battery_capacity > 0),
    engine_volume    NUMERIC(3, 1)   NOT NULL CHECK (engine_volume > 0 AND engine_volume <= 10.0)
);

CREATE TABLE fleet.internal_combustion_powertrain
(
    powertrain_id UUID PRIMARY KEY REFERENCES fleet.powertrain_specification (id) ON DELETE CASCADE,
    fuel_type     fleet.fuel_type NOT NULL,
    engine_volume NUMERIC(3, 1)   NOT NULL CHECK (engine_volume > 0 AND engine_volume <= 10.0)
);