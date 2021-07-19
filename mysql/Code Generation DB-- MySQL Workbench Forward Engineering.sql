-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecommerce_7emearche
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ecommerce_7emearche
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecommerce_7emearche` DEFAULT CHARACTER SET utf8 ;
USE `ecommerce_7emearche` ;

-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`article` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(100) NULL,
  `resume` VARCHAR(500) NULL,
  `prix_ht` DECIMAL(9) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`adresse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`adresse` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`adresse` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero_rue` VARCHAR(10) NULL,
  `nom_rue` VARCHAR(100) NULL,
  `code_postal` VARCHAR(10) NULL,
  `ville` VARCHAR(100) NULL,
  `pays` VARCHAR(50) NULL,
  `complement` VARCHAR(200) NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`editeur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`editeur` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`editeur` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(100) NULL,
  `adresse_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_editeur_adresse1`
    FOREIGN KEY (`adresse_id`)
    REFERENCES `ecommerce_7emearche`.`adresse` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`livre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`livre` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`livre` (
  `isbn13` VARCHAR(17) NOT NULL,
  `titre_livre` VARCHAR(100) NULL,
  `format` VARCHAR(60) NULL,
  `lien_image` VARCHAR(100) NULL,
  `nb_pages` INT NULL,
  `date_depot_legal` DATE NULL,
  `article_id` INT NOT NULL,
  `editeur_id` INT NOT NULL,
  PRIMARY KEY (`isbn13`),
  INDEX `fk_livre_article1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_livre_editeur1_idx` (`editeur_id` ASC) VISIBLE,
  UNIQUE INDEX `isbn13_UNIQUE` (`isbn13` ASC) VISIBLE,
  UNIQUE INDEX `article_id_UNIQUE` (`article_id` ASC) VISIBLE,
  CONSTRAINT `fk_livre_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `ecommerce_7emearche`.`article` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_livre_editeur1`
    FOREIGN KEY (`editeur_id`)
    REFERENCES `ecommerce_7emearche`.`editeur` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`personne`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`personne` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`personne` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(40) NULL,
  `prenom` VARCHAR(40) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`auteur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`auteur` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`auteur` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `personne_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_auteur_personne1_idx` (`personne_id` ASC) VISIBLE,
  UNIQUE INDEX `personne_id_UNIQUE` (`personne_id` ASC) VISIBLE,
  CONSTRAINT `fk_auteur_personne1`
    FOREIGN KEY (`personne_id`)
    REFERENCES `ecommerce_7emearche`.`personne` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`client` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(80) NULL,
  `mot_de_passe` VARCHAR(80) NULL,
  `numero_carte` VARCHAR(80) NULL,
  `date_validite` VARCHAR(80) NULL,
  `cvc` VARCHAR(80) NULL,
  `adresse_facturation_id` INT NOT NULL,
  `adresse_livraison_id` INT NOT NULL,
  `personne_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_client_adresse1_idx` (`adresse_facturation_id` ASC) VISIBLE,
  INDEX `fk_client_adresse2_idx` (`adresse_livraison_id` ASC) VISIBLE,
  INDEX `fk_client_personne1_idx` (`personne_id` ASC) VISIBLE,
  UNIQUE INDEX `personne_id_UNIQUE` (`personne_id` ASC) VISIBLE,
  CONSTRAINT `fk_client_adresse1`
    FOREIGN KEY (`adresse_facturation_id`)
    REFERENCES `ecommerce_7emearche`.`adresse` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_adresse2`
    FOREIGN KEY (`adresse_livraison_id`)
    REFERENCES `ecommerce_7emearche`.`adresse` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_personne1`
    FOREIGN KEY (`personne_id`)
    REFERENCES `ecommerce_7emearche`.`personne` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`commande`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`commande` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`commande` (
  `numero` VARCHAR(21) NOT NULL,
  `status` VARCHAR(20) NULL,
  `date` DATETIME NULL,
  `client_id` INT NOT NULL,
  PRIMARY KEY (`numero`),
  INDEX `fk_commande_client1_idx` (`client_id` ASC) VISIBLE,
  UNIQUE INDEX `numero_UNIQUE` (`numero` ASC) VISIBLE,
  CONSTRAINT `fk_commande_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `ecommerce_7emearche`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`ligne_commande`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`ligne_commande` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`ligne_commande` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantite_commandee` INT NULL,
  `prix_ht` DECIMAL(9) NULL,
  `prix_ttc` DECIMAL(9) NULL,
  `article_id` INT NOT NULL,
  `commande_numero` VARCHAR(21) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_article_has_commande_article1_idx` (`article_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_ligne_commande_commande1_idx` (`commande_numero` ASC) VISIBLE,
  CONSTRAINT `fk_article_has_commande_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `ecommerce_7emearche`.`article` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ligne_commande_commande1`
    FOREIGN KEY (`commande_numero`)
    REFERENCES `ecommerce_7emearche`.`commande` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`facture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`facture` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`facture` (
  `numero` VARCHAR(24) NOT NULL,
  `status` VARCHAR(50) NULL,
  `commande_numero` VARCHAR(21) NOT NULL,
  PRIMARY KEY (`numero`),
  INDEX `fk_facture_commande1_idx` (`commande_numero` ASC) VISIBLE,
  UNIQUE INDEX `numero_UNIQUE` (`numero` ASC) VISIBLE,
  CONSTRAINT `fk_facture_commande1`
    FOREIGN KEY (`commande_numero`)
    REFERENCES `ecommerce_7emearche`.`commande` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`consultation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`consultation` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`consultation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `article_id` INT NOT NULL,
  `client_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_table1_article1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_table1_client1_idx` (`client_id` ASC) VISIBLE,
  CONSTRAINT `fk_table1_article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `ecommerce_7emearche`.`article` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `ecommerce_7emearche`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`livre_numerique`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`livre_numerique` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`livre_numerique` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `espace` DECIMAL(6,1) NULL,
  `unite` VARCHAR(5) NULL,
  `livre_isbn13` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_livre_numerique_livre1_idx` (`livre_isbn13` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `livre_isbn13_UNIQUE` (`livre_isbn13` ASC) VISIBLE,
  CONSTRAINT `fk_livre_numerique_livre1`
    FOREIGN KEY (`livre_isbn13`)
    REFERENCES `ecommerce_7emearche`.`livre` (`isbn13`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`livre_imprime`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`livre_imprime` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`livre_imprime` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantite_stock` INT NULL,
  `date_fin_tirage` DATE NULL,
  `date_reimpression` DATE NULL,
  `poids` DECIMAL(6,1) NULL,
  `unite_poids` VARCHAR(5) NULL,
  `longueur` DECIMAL(6,1) NULL,
  `largeur` DECIMAL(6,1) NULL,
  `epaisseur` DECIMAL(7,2) NULL,
  `unite_longueur` VARCHAR(5) NULL,
  `livre_isbn13` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_livre_imprime_livre1_idx` (`livre_isbn13` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `livre_isbn13_UNIQUE` (`livre_isbn13` ASC) VISIBLE,
  CONSTRAINT `fk_livre_imprime_livre1`
    FOREIGN KEY (`livre_isbn13`)
    REFERENCES `ecommerce_7emearche`.`livre` (`isbn13`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`genre` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(30) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`auteur_livre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`auteur_livre` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`auteur_livre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `auteur_id` INT NOT NULL,
  `livre_isbn13` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_auteur_livre_auteur1_idx` (`auteur_id` ASC) VISIBLE,
  INDEX `fk_auteur_livre_livre1_idx` (`livre_isbn13` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_auteur_livre_auteur1`
    FOREIGN KEY (`auteur_id`)
    REFERENCES `ecommerce_7emearche`.`auteur` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_auteur_livre_livre1`
    FOREIGN KEY (`livre_isbn13`)
    REFERENCES `ecommerce_7emearche`.`livre` (`isbn13`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce_7emearche`.`livre_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce_7emearche`.`livre_genre` ;

CREATE TABLE IF NOT EXISTS `ecommerce_7emearche`.`livre_genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genre_id` INT NOT NULL,
  `livre_isbn13` VARCHAR(17) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_livre_genre_genre1_idx` (`genre_id` ASC) VISIBLE,
  INDEX `fk_livre_genre_livre1_idx` (`livre_isbn13` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_livre_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `ecommerce_7emearche`.`genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_livre_genre_livre1`
    FOREIGN KEY (`livre_isbn13`)
    REFERENCES `ecommerce_7emearche`.`livre` (`isbn13`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;