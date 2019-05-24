-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema airlinesdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema airlinesdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `airlinesdb` DEFAULT CHARACTER SET utf8 ;
USE `airlinesdb` ;

-- -----------------------------------------------------
-- Table `airlinesdb`.`agencies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`agencies` (
  `agency_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `user` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`agency_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airlinesdb`.`aircrafts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`aircrafts` (
  `aircraft_id` INT(11) NOT NULL,
  `number` VARCHAR(45) NULL DEFAULT NULL,
  `capacity` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`aircraft_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airlinesdb`.`airports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`airports` (
  `airport_id` VARCHAR(5) NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `elevation` INT(11) NULL DEFAULT NULL,
  `continent` VARCHAR(4) NULL DEFAULT NULL,
  `country` VARCHAR(4) NULL DEFAULT NULL,
  `region` VARCHAR(10) NULL DEFAULT NULL,
  `munipality` VARCHAR(200) NULL DEFAULT NULL,
  `gps_code` VARCHAR(10) NULL DEFAULT NULL,
  `local_code` VARCHAR(10) NULL DEFAULT NULL,
  `coordinates` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`airport_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airlinesdb`.`flight_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`flight_schedule` (
  `flight_id` INT(11) NOT NULL,
  `takeoff_date` DATETIME NULL DEFAULT NULL,
  `arrival_date` DATETIME NULL DEFAULT NULL,
  `flight_rate` FLOAT NULL DEFAULT NULL,
  `airport_takeoff` VARCHAR(5) NOT NULL,
  `airports_arrival` VARCHAR(5) NOT NULL,
  `aircraft_id` INT(11) NOT NULL,
  PRIMARY KEY (`flight_id`),
  INDEX `fk_flight_schedule_airports_idx` (`airport_takeoff` ASC) VISIBLE,
  INDEX `fk_flight_schedule_airports1_idx` (`airports_arrival` ASC) VISIBLE,
  INDEX `fk_flight_schedule_aircrafts1_idx` (`aircraft_id` ASC) VISIBLE,
  CONSTRAINT `fk_flight_schedule_aircrafts1`
    FOREIGN KEY (`aircraft_id`)
    REFERENCES `airlinesdb`.`aircrafts` (`aircraft_id`),
  CONSTRAINT `fk_flight_schedule_airports`
    FOREIGN KEY (`airport_takeoff`)
    REFERENCES `airlinesdb`.`airports` (`airport_id`),
  CONSTRAINT `fk_flight_schedule_airports1`
    FOREIGN KEY (`airports_arrival`)
    REFERENCES `airlinesdb`.`airports` (`airport_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airlinesdb`.`passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`passenger` (
  `pass_dni` VARCHAR(20) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`pass_dni`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airlinesdb`.`reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`reservations` (
  `reservation_id` INT(11) NOT NULL,
  `reservation_Date` DATETIME NULL DEFAULT NULL,
  `airports_airport_id` VARCHAR(5) NOT NULL,
  `agencies_agency_id` INT(11) NOT NULL,
  PRIMARY KEY (`reservation_id`),
  INDEX `fk_reservations_airports1_idx` (`airports_airport_id` ASC) VISIBLE,
  INDEX `fk_reservations_agencies1_idx` (`agencies_agency_id` ASC) VISIBLE,
  CONSTRAINT `fk_reservations_agencies1`
    FOREIGN KEY (`agencies_agency_id`)
    REFERENCES `airlinesdb`.`agencies` (`agency_id`),
  CONSTRAINT `fk_reservations_airports1`
    FOREIGN KEY (`airports_airport_id`)
    REFERENCES `airlinesdb`.`airports` (`airport_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `airlinesdb`.`reservation_passengers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airlinesdb`.`reservation_passengers` (
  `reservation_id` INT(11) NOT NULL,
  `pass_dni` VARCHAR(20) NOT NULL,
  `luggages_quantity` INT(11) NULL DEFAULT NULL,
  `priority` TINYINT(4) NULL DEFAULT NULL,
  `seat_number` VARCHAR(6) NULL DEFAULT NULL,
  PRIMARY KEY (`reservation_id`, `pass_dni`),
  INDEX `fk_reservation_passengers_reservations1_idx` (`reservation_id` ASC) VISIBLE,
  INDEX `fk_reservation_passengers_passenger1_idx` (`pass_dni` ASC) VISIBLE,
  CONSTRAINT `fk_reservation_passengers_passenger1`
    FOREIGN KEY (`pass_dni`)
    REFERENCES `airlinesdb`.`passenger` (`pass_dni`),
  CONSTRAINT `fk_reservation_passengers_reservations1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `airlinesdb`.`reservations` (`reservation_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
