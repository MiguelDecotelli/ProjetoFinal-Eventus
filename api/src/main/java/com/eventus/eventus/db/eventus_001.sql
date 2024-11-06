CREATE SCHEMA IF NOT EXISTS `eventus` ;
USE `eventus` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`Cities` (
  `city_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE INDEX `id_cities_UNIQUE` (`city_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`Users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `city` INT NOT NULL,
  `birthday` DATE NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `Cities_city_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `Cities_city_id`),
  UNIQUE INDEX `idUsers_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_Users_Cities1_idx` (`Cities_city_id` ASC) VISIBLE,
  CONSTRAINT `fk_Users_Cities1`
    FOREIGN KEY (`Cities_city_id`)
    REFERENCES `mydb`.`Cities` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EventAddress`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`EventAddress` (
  `EventAddress_id` INT NOT NULL AUTO_INCREMENT,
  `cep` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `street_number` INT NOT NULL,
  `complement` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `Cities_city_id` INT NOT NULL,
  PRIMARY KEY (`EventAddress_id`, `Cities_city_id`),
  UNIQUE INDEX `EventAddress_id_UNIQUE` (`EventAddress_id` ASC) VISIBLE,
  UNIQUE INDEX `number_UNIQUE` (`street_number` ASC) VISIBLE,
  INDEX `fk_EventAddress_Cities1_idx` (`Cities_city_id` ASC) VISIBLE,
  CONSTRAINT `fk_EventAddress_Cities1`
    FOREIGN KEY (`Cities_city_id`)
    REFERENCES `mydb`.`Cities` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`Events` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `event_name` VARCHAR(45) NOT NULL,
  `initial_date` VARCHAR(45) NOT NULL,
  `final_date` VARCHAR(45) NOT NULL,
  `description` TEXT(2550) NOT NULL ,
  `event_image` BLOB NOT NULL,
  `event_status` ENUM("Active", "Concluded", "Pending", "Postponed") NOT NULL,
  `EventAddress_EventAddress_id` INT NOT NULL,
  PRIMARY KEY (`event_id`, `EventAddress_EventAddress_id`),
  UNIQUE INDEX `id_events_UNIQUE` (`event_id` ASC) VISIBLE,
  INDEX `fk_Events_EventAddress1_idx` (`EventAddress_EventAddress_id` ASC) VISIBLE,
  CONSTRAINT `fk_Events_EventAddress1`
    FOREIGN KEY (`EventAddress_EventAddress_id`)
    REFERENCES `mydb`.`EventAddress` (`EventAddress_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`Tickets` (
  `ticket_id` INT NOT NULL AUTO_INCREMENT,
  `ticket_name` VARCHAR(255) NULL,
  `ticket_value` FLOAT NULL,
  `ticket_description` TEXT(2550) NULL,
  `ticket_amount` INT NULL,
  PRIMARY KEY (`ticket_id`),
  UNIQUE INDEX `ticket_id_UNIQUE` (`ticket_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UsersTickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`UsersTickets` (
  `UsersTickets_id` INT NOT NULL AUTO_INCREMENT,
  `Users_user_id` INT NOT NULL,
  `Tickets_ticket_id` INT NOT NULL,
  `ticket_status` ENUM("Active", "Cancelled", "Pending", "Expired") NOT NULL,
  `api_response` BLOB NOT NULL,
  INDEX `fk_Users_has_Tickets_Tickets1_idx` (`Tickets_ticket_id` ASC) VISIBLE,
  INDEX `fk_Users_has_Tickets_Users1_idx` (`Users_user_id` ASC) VISIBLE,
  PRIMARY KEY (`UsersTickets_id`),
  CONSTRAINT `fk_Users_has_Tickets_Users1`
    FOREIGN KEY (`Users_user_id`)
    REFERENCES `mydb`.`Users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Tickets_Tickets1`
    FOREIGN KEY (`Tickets_ticket_id`)
    REFERENCES `mydb`.`Tickets` (`ticket_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

