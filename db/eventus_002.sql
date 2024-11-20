
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
    REFERENCES `eventus`.`Cities` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EventAddress`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`Address` (
  `Address_id` INT NOT NULL AUTO_INCREMENT,
  `cep` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `street_number` INT NOT NULL,
  `complement` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `City_id` INT NOT NULL,
  PRIMARY KEY (`Address_id`, `City_id`),
  UNIQUE INDEX `Address_id_UNIQUE` (`Address_id` ASC) VISIBLE,
  UNIQUE INDEX `number_UNIQUE` (`street_number` ASC) VISIBLE,
  INDEX `fk_Address_Cities1_idx` (`Cities_city_id` ASC) VISIBLE,
  CONSTRAINT `fk_Address_Cities1`
    FOREIGN KEY (`City_id`)
    REFERENCES `eventus`.`Cities` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`Events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `initial_date` VARCHAR(45) NOT NULL,
  `final_date` VARCHAR(45) NOT NULL,
  `description` TEXT(2550) NOT NULL ,
  `image` BLOB NOT NULL,
  `status` ENUM("Active", "Concluded", "Pending", "Postponed") NOT NULL,
  `Address_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Address_id`),
  UNIQUE INDEX `id_events_UNIQUE` (`event_id` ASC) VISIBLE,
  INDEX `fk_Events_Address1_idx` (`Address_Address_id` ASC) VISIBLE,
  CONSTRAINT `fk_Events_Address1`
    FOREIGN KEY (`Address_id`)
    REFERENCES `eventus`.`Address` (`Address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`Tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `value` FLOAT NULL,
  `description` TEXT(2550) NULL,
  `amount` INT NULL,
  `events_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
  CONSTRAINT `fk_Events_id1`
      FOREIGN KEY (`events_id`)
      REFERENCES `eventus`.`events` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UsersTickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventus`.`UsersTickets` (
  `UsersTickets_id` INT NOT NULL AUTO_INCREMENT,
  `Users_id` INT NOT NULL,
  `Tickets_id` INT NOT NULL,
  `ticket_status` ENUM("Active", "Cancelled", "Pending", "Expired") NOT NULL,
  `api_response` BLOB NOT NULL,
  INDEX `fk_Users_has_Tickets_Tickets1_idx` (`Tickets_ticket_id` ASC) VISIBLE,
  INDEX `fk_Users_has_Tickets_Users1_idx` (`Users_user_id` ASC) VISIBLE,
  PRIMARY KEY (`UsersTickets_id`),
  CONSTRAINT `fk_Users_has_Tickets_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `eventus`.`Users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Tickets_Tickets1`
    FOREIGN KEY (`Tickets_id`)
    REFERENCES `eventus`.`Tickets` (`ticket_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;