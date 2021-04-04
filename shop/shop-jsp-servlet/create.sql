CREATE SCHEMA `internet_shop` ;

CREATE TABLE `internet_shop`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cash` DOUBLE NOT NULL,
  `currencyId` INT NOT NULL,
  `pass` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  CREATE TABLE `internet_shop`.`bucket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customerId` INT NOT NULL,
  `productId` INT NOT NULL,
  `processed` INT NOT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `internet_shop`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customerId` INT NOT NULL,
  `productId` INT NOT NULL,
  `processed_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  CREATE TABLE `internet_shop`.`currency` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `koef` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  CREATE TABLE `internet_shop`.`products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `currencyId` int NOT NULL,
  `eatable` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) 

DROP TRIGGER IF EXISTS save_processed_order;

DELIMITER $$

CREATE TRIGGER save_processed_order BEFORE UPDATE ON internet_shop.bucket
FOR EACH ROW BEGIN
  IF (NEW.processed = 1) THEN
		INSERT INTO orders (customerId, productId, processed_date) VALUES (OLD.customerId, OLD.productId, CURDATE());
  END IF;
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS internet_shop.get_customer_history; 
CREATE PROCEDURE internet_shop.get_customer_history(IN custId INT) 
	SELECT * FROM orders
		JOIN products ON productId = products.id
		JOIN currency ON products.currencyId = currency.id
	WHERE customerId = custId;