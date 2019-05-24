-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema test_flights
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema test_flights
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_flights` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema airlinesdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema airlinesdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `airlinesdb` DEFAULT CHARACTER SET utf8 ;
USE `test_flights` ;

-- -----------------------------------------------------
-- Table `test_flights`.`aircrafts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_flights`.`aircrafts` (
  `AcID` INT(11) NOT NULL,
  `AcNumber` VARCHAR(32) NOT NULL,
  `Capacity` INT(11) NOT NULL,
  PRIMARY KEY (`AcID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_flights`.`airports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_flights`.`airports` (
  `AirID` VARCHAR(10) NOT NULL,
  `Name` VARCHAR(32) NOT NULL,
  `Country` VARCHAR(120) NULL DEFAULT NULL,
  `City` VARCHAR(32) NULL DEFAULT NULL,
  PRIMARY KEY (`AirID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_flights`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_flights`.`customers` (
  `CnID` INT(11) NOT NULL,
  `Email` VARCHAR(32) NOT NULL,
  `Cell` VARCHAR(16) NOT NULL,
  `STREET` VARCHAR(32) NULL DEFAULT NULL,
  PRIMARY KEY (`CnID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_flights`.`flight_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_flights`.`flight_schedule` (
  `FIID` INT(11) NOT NULL,
  `FlightDate` DATETIME NULL DEFAULT NULL,
  `Arrival` DATETIME NULL DEFAULT NULL,
  `AircraftID` INT(11) NULL DEFAULT NULL,
  `AirportStart` VARCHAR(10) NULL DEFAULT NULL,
  `AirportEnd` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`FIID`),
  INDEX `fk_Aircraft` (`AircraftID` ASC) VISIBLE,
  INDEX `fk_AirportEnd` (`AirportEnd` ASC) VISIBLE,
  INDEX `fk_AirportStart` (`AirportStart` ASC) VISIBLE,
  CONSTRAINT `fk_Aircraft`
    FOREIGN KEY (`AircraftID`)
    REFERENCES `test_flights`.`aircrafts` (`AcID`),
  CONSTRAINT `fk_AirportEnd`
    FOREIGN KEY (`AirportEnd`)
    REFERENCES `test_flights`.`airports` (`AirID`),
  CONSTRAINT `fk_AirportStart`
    FOREIGN KEY (`AirportStart`)
    REFERENCES `test_flights`.`airports` (`AirID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_flights`.`passengers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_flights`.`passengers` (
  `PsID` INT(11) NOT NULL,
  `Name` VARCHAR(32) NOT NULL,
  `Address` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`PsID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_flights`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_flights`.`transactions` (
  `TsID` INT(11) NOT NULL,
  `BookingDate` DATETIME NULL DEFAULT NULL,
  `Departure` DATETIME NULL DEFAULT NULL,
  `Passenger` INT(11) NULL DEFAULT NULL,
  `Flight` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`TsID`),
  INDEX `fk_passenger` (`Passenger` ASC) VISIBLE,
  INDEX `fk_flight` (`Flight` ASC) VISIBLE,
  CONSTRAINT `fk_flight`
    FOREIGN KEY (`Flight`)
    REFERENCES `test_flights`.`flight_schedule` (`FIID`),
  CONSTRAINT `fk_passenger`
    FOREIGN KEY (`Passenger`)
    REFERENCES `test_flights`.`passengers` (`PsID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `airlinesdb` ;

-- -----------------------------------------------------
-- Table `airlinesdb`.`passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`passenger` (
  `pass_dni` VARCHAR(20) NOT NULL,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  PRIMARY KEY (`pass_dni`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airlinesdb`.`aircrafts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`aircrafts` (
  `aircraft_id` INT NOT NULL,
  `number` VARCHAR(45) NULL,
  `capacity` INT NULL,
  PRIMARY KEY (`aircraft_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airlinesdb`.`airports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`airports` (
  `airport_id` VARCHAR(5) NOT NULL,
  `type` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `elevation` INT NULL,
  `continent` VARCHAR(4) NULL,
  `country` VARCHAR(4) NULL,
  `region` VARCHAR(10) NULL,
  `munipality` VARCHAR(200) NULL,
  `gps_code` VARCHAR(10) NULL,
  `local_code` VARCHAR(10) NULL,
  `coordinates` VARCHAR(300) NULL,
  PRIMARY KEY (`airport_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airlinesdb`.`agencies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`agencies` (
  `agency_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `user` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`agency_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airlinesdb`.`flight_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`flight_schedule` (
  `flight_id` INT NOT NULL,
  `takeoff_date` DATETIME NULL,
  `arrival_date` DATETIME NULL,
  `flight_rate` FLOAT NULL,
  `airport_takeoff` VARCHAR(5) NOT NULL,
  `airports_arrival` VARCHAR(5) NOT NULL,
  `aircraft_id` INT NOT NULL,
  PRIMARY KEY (`flight_id`),
  INDEX `fk_flight_schedule_airports_idx` (`airport_takeoff` ASC) VISIBLE,
  INDEX `fk_flight_schedule_airports1_idx` (`airports_arrival` ASC) VISIBLE,
  INDEX `fk_flight_schedule_aircrafts1_idx` (`aircraft_id` ASC) VISIBLE,
  CONSTRAINT `fk_flight_schedule_airports`
    FOREIGN KEY (`airport_takeoff`)
    REFERENCES `airlinesdb`.`airports` (`airport_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flight_schedule_airports1`
    FOREIGN KEY (`airports_arrival`)
    REFERENCES `airlinesdb`.`airports` (`airport_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flight_schedule_aircrafts1`
    FOREIGN KEY (`aircraft_id`)
    REFERENCES `airlinesdb`.`aircrafts` (`aircraft_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airlinesdb`.`reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`reservations` (
  `reservation_id` INT NOT NULL,
  `reservation_Date` DATETIME NULL,
  `airports_airport_id` VARCHAR(5) NOT NULL,
  `agencies_agency_id` INT NOT NULL,
  PRIMARY KEY (`reservation_id`),
  INDEX `fk_reservations_airports1_idx` (`airports_airport_id` ASC) VISIBLE,
  INDEX `fk_reservations_agencies1_idx` (`agencies_agency_id` ASC) VISIBLE,
  CONSTRAINT `fk_reservations_airports1`
    FOREIGN KEY (`airports_airport_id`)
    REFERENCES `airlinesdb`.`airports` (`airport_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservations_agencies1`
    FOREIGN KEY (`agencies_agency_id`)
    REFERENCES `airlinesdb`.`agencies` (`agency_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airlinesdb`.`reservation_passengers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`reservation_passengers` (
  `reservation_id` INT NOT NULL,
  `pass_dni` VARCHAR(20) NOT NULL,
  `luggages_quantity` INT NULL,
  `priority` TINYINT NULL,
  `seat_number` VARCHAR(6) NULL,
  INDEX `fk_reservation_passengers_reservations1_idx` (`reservation_id` ASC) VISIBLE,
  INDEX `fk_reservation_passengers_passenger1_idx` (`pass_dni` ASC) VISIBLE,
  PRIMARY KEY (`reservation_id`, `pass_dni`),
  CONSTRAINT `fk_reservation_passengers_reservations1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `airlinesdb`.`reservations` (`reservation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_passengers_passenger1`
    FOREIGN KEY (`pass_dni`)
    REFERENCES `airlinesdb`.`passenger` (`pass_dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
