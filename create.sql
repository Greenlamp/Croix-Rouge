SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `croixrouge` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `croixrouge` ;

-- -----------------------------------------------------
-- Table `croixrouge`.`PersonneUrgence`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`PersonneUrgence` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`PersonneUrgence` (
  `idPersonneUrgence` INT NOT NULL AUTO_INCREMENT ,
  `nom` VARCHAR(255) NULL ,
  `prenom` VARCHAR(255) NULL ,
  `telephone` VARCHAR(255) NULL ,
  PRIMARY KEY (`idPersonneUrgence`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Decouverte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Decouverte` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Decouverte` (
  `idDecouverte` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`idDecouverte`) ,
  UNIQUE INDEX `description_UNIQUE` (`description` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Prestation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Prestation` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Prestation` (
  `idPrestation` INT NOT NULL AUTO_INCREMENT ,
  `datePrestation` VARCHAR(255) NULL ,
  `heurePrestée` VARCHAR(255) NULL ,
  `points` INT(4) NULL ,
  `pointsDepense` INT(4) NULL ,
  PRIMARY KEY (`idPrestation`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Langue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Langue` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Langue` (
  `idLangue` INT NOT NULL AUTO_INCREMENT ,
  `nom` VARCHAR(255) NULL ,
  PRIMARY KEY (`idLangue`) ,
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Renseignements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Renseignements` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Renseignements` (
  `idRenseignement` INT NOT NULL AUTO_INCREMENT ,
  `activitePro` VARCHAR(255) NULL ,
  `SituationActuelle` VARCHAR(255) NULL ,
  `Diplome` VARCHAR(255) NULL ,
  `PermisConduire` VARCHAR(255) NULL ,
  `Categorie` VARCHAR(255) NULL ,
  `DateObtention` DATETIME NULL ,
  `selectionMedicale` VARCHAR(255) NULL ,
  `dateValidite` VARCHAR(255) NULL ,
  `numCompteBancaire` VARCHAR(255) NULL ,
  `idLangueMaternelle` INT NULL ,
  PRIMARY KEY (`idRenseignement`) ,
  INDEX `fk_Renseignements_Langue1_idx` (`idLangueMaternelle` ASC) ,
  CONSTRAINT `fk_Renseignements_Langue1`
    FOREIGN KEY (`idLangueMaternelle` )
    REFERENCES `croixrouge`.`Langue` (`idLangue` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Pays`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Pays` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Pays` (
  `idPays` INT NOT NULL AUTO_INCREMENT ,
  `nomPays` VARCHAR(255) NULL ,
  PRIMARY KEY (`idPays`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Adresse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Adresse` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Adresse` (
  `idAdresse` INT NOT NULL AUTO_INCREMENT ,
  `rueAvenueBd` VARCHAR(255) NULL ,
  `numero` VARCHAR(255) NULL ,
  `codePostal` VARCHAR(255) NULL ,
  `boite` VARCHAR(255) NULL ,
  `idPays` INT NULL ,
  `matriculeVolontaire` VARCHAR(255) NULL ,
  PRIMARY KEY (`idAdresse`) ,
  INDEX `fk_Adresse_Pays1_idx` (`idPays` ASC) ,
  INDEX `fk_Adresse_Volontaires1_idx` (`matriculeVolontaire` ASC) ,
  CONSTRAINT `fk_Adresse_Pays1`
    FOREIGN KEY (`idPays` )
    REFERENCES `croixrouge`.`Pays` (`idPays` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Adresse_Volontaires1`
    FOREIGN KEY (`matriculeVolontaire` )
    REFERENCES `croixrouge`.`Volontaires` (`matricule` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Telephone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Telephone` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Telephone` (
  `idTelephone` INT NOT NULL AUTO_INCREMENT ,
  `gsm` VARCHAR(255) NULL ,
  `autreGsm` VARCHAR(255) NULL ,
  `telephoneFix` VARCHAR(255) NULL ,
  `telephonePro` VARCHAR(255) NULL ,
  `fax` VARCHAR(255) NULL ,
  PRIMARY KEY (`idTelephone`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Activite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Activite` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Activite` (
  `idActivite` INT NOT NULL AUTO_INCREMENT ,
  `dateDebut` VARCHAR(255) NULL ,
  `centreSecours` VARCHAR(255) NULL ,
  `sisu` VARCHAR(255) NULL ,
  `copiePermis` VARCHAR(255) NULL ,
  `numeroRegistreNational` VARCHAR(255) NULL ,
  PRIMARY KEY (`idActivite`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Volontaires`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Volontaires` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Volontaires` (
  `matricule` VARCHAR(255) NOT NULL ,
  `nom` VARCHAR(255) NOT NULL ,
  `nomEpouse` VARCHAR(255) NULL ,
  `prenom` VARCHAR(255) NOT NULL ,
  `dateNaissance` DATETIME NOT NULL ,
  `sexe` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NULL ,
  `remarques` VARCHAR(255) NULL ,
  `idPersonneUrgence` INT NULL ,
  `idDecouverte` INT NULL ,
  `idPrestation` INT NULL ,
  `idRenseignement` INT NULL ,
  `idAdresseLegale` INT NULL ,
  `idAdresseResidence` INT NULL ,
  `idTelephone` INT NULL ,
  `idActivite` INT NULL ,
  PRIMARY KEY (`matricule`) ,
  INDEX `fk_Volontaires_PersonneUrgence_idx` (`idPersonneUrgence` ASC) ,
  INDEX `fk_Volontaires_Decouverte1_idx` (`idDecouverte` ASC) ,
  INDEX `fk_Volontaires_Prestation1_idx` (`idPrestation` ASC) ,
  INDEX `fk_Volontaires_Renseignements1_idx` (`idRenseignement` ASC) ,
  INDEX `fk_Volontaires_Adresse1_idx` (`idAdresseLegale` ASC) ,
  INDEX `fk_Volontaires_Adresse2_idx` (`idAdresseResidence` ASC) ,
  INDEX `fk_Volontaires_Telephone1_idx` (`idTelephone` ASC) ,
  INDEX `fk_Volontaires_Activite1_idx` (`idActivite` ASC) ,
  CONSTRAINT `fk_Volontaires_PersonneUrgence`
    FOREIGN KEY (`idPersonneUrgence` )
    REFERENCES `croixrouge`.`PersonneUrgence` (`idPersonneUrgence` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Volontaires_Decouverte1`
    FOREIGN KEY (`idDecouverte` )
    REFERENCES `croixrouge`.`Decouverte` (`idDecouverte` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Volontaires_Prestation1`
    FOREIGN KEY (`idPrestation` )
    REFERENCES `croixrouge`.`Prestation` (`idPrestation` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Volontaires_Renseignements1`
    FOREIGN KEY (`idRenseignement` )
    REFERENCES `croixrouge`.`Renseignements` (`idRenseignement` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Volontaires_Adresse1`
    FOREIGN KEY (`idAdresseLegale` )
    REFERENCES `croixrouge`.`Adresse` (`idAdresse` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Volontaires_Adresse2`
    FOREIGN KEY (`idAdresseResidence` )
    REFERENCES `croixrouge`.`Adresse` (`idAdresse` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Volontaires_Telephone1`
    FOREIGN KEY (`idTelephone` )
    REFERENCES `croixrouge`.`Telephone` (`idTelephone` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Volontaires_Activite1`
    FOREIGN KEY (`idActivite` )
    REFERENCES `croixrouge`.`Activite` (`idActivite` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`FormationActivite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`FormationActivite` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`FormationActivite` (
  `idFormationActivite` INT NOT NULL AUTO_INCREMENT ,
  `nomFormationActivite` VARCHAR(255) NULL ,
  `status` VARCHAR(255) NULL ,
  PRIMARY KEY (`idFormationActivite`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Ville`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Ville` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Ville` (
  `idVille` INT NOT NULL AUTO_INCREMENT ,
  `nomVille` VARCHAR(255) NULL ,
  PRIMARY KEY (`idVille`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Formation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Formation` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Formation` (
  `idFormation` INT NOT NULL AUTO_INCREMENT ,
  `nomFormation` VARCHAR(255) NULL ,
  `dateObtention` DATETIME NULL ,
  `dateExpiration` DATETIME NULL ,
  `numero` VARCHAR(255) NULL ,
  `lieu` VARCHAR(255) NULL ,
  `copie` BLOB NULL ,
  `numeroService112` VARCHAR(255) NULL ,
  PRIMARY KEY (`idFormation`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`LanguesConnue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`LanguesConnue` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`LanguesConnue` (
  `idRenseignements` INT NOT NULL ,
  `idLangue` INT NOT NULL ,
  PRIMARY KEY (`idRenseignements`, `idLangue`) ,
  INDEX `fk_LanguesConnue_Renseignements1_idx` (`idRenseignements` ASC) ,
  INDEX `fk_LanguesConnue_Langue1_idx` (`idLangue` ASC) ,
  CONSTRAINT `fk_LanguesConnue_Renseignements1`
    FOREIGN KEY (`idRenseignements` )
    REFERENCES `croixrouge`.`Renseignements` (`idRenseignement` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LanguesConnue_Langue1`
    FOREIGN KEY (`idLangue` )
    REFERENCES `croixrouge`.`Langue` (`idLangue` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`FormationSuivieActivite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`FormationSuivieActivite` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`FormationSuivieActivite` (
  `idActivite` INT NOT NULL ,
  `idFormationActivite` INT NOT NULL ,
  PRIMARY KEY (`idActivite`, `idFormationActivite`) ,
  INDEX `fk_FormationSuivieActivite_Activite1_idx` (`idActivite` ASC) ,
  INDEX `fk_FormationSuivieActivite_FormationActivite1_idx` (`idFormationActivite` ASC) ,
  CONSTRAINT `fk_FormationSuivieActivite_Activite1`
    FOREIGN KEY (`idActivite` )
    REFERENCES `croixrouge`.`Activite` (`idActivite` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FormationSuivieActivite_FormationActivite1`
    FOREIGN KEY (`idFormationActivite` )
    REFERENCES `croixrouge`.`FormationActivite` (`idFormationActivite` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Utilisateurs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Utilisateurs` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Utilisateurs` (
  `idUtilisateur` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(255) NULL ,
  `password` VARCHAR(255) NULL ,
  `dateDerniereConnexion` DATETIME NULL ,
  `connected` INT NULL ,
  `prioritaire` INT NULL ,
  PRIMARY KEY (`idUtilisateur`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Groupes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Groupes` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Groupes` (
  `idGroupe` INT NOT NULL AUTO_INCREMENT ,
  `nomGroupe` VARCHAR(255) NULL ,
  `niveau` INT(2) NULL ,
  PRIMARY KEY (`idGroupe`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Droits`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Droits` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Droits` (
  `idDroit` INT NOT NULL AUTO_INCREMENT ,
  `nom` VARCHAR(255) NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`idDroit`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Appartenir`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Appartenir` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Appartenir` (
  `idUtilisateur` INT NOT NULL ,
  `idGroupe` INT NOT NULL ,
  PRIMARY KEY (`idUtilisateur`, `idGroupe`) ,
  INDEX `fk_Appartenir_Administration1_idx` (`idUtilisateur` ASC) ,
  INDEX `fk_Appartenir_Groupes1_idx` (`idGroupe` ASC) ,
  CONSTRAINT `fk_Appartenir_Administration1`
    FOREIGN KEY (`idUtilisateur` )
    REFERENCES `croixrouge`.`Utilisateurs` (`idUtilisateur` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appartenir_Groupes1`
    FOREIGN KEY (`idGroupe` )
    REFERENCES `croixrouge`.`Groupes` (`idGroupe` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`PossederDroit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`PossederDroit` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`PossederDroit` (
  `idGroupe` INT NOT NULL ,
  `idDroit` INT NOT NULL ,
  PRIMARY KEY (`idGroupe`, `idDroit`) ,
  INDEX `fk_PossederDroit_Groupes1_idx` (`idGroupe` ASC) ,
  INDEX `fk_PossederDroit_Droits1_idx` (`idDroit` ASC) ,
  CONSTRAINT `fk_PossederDroit_Groupes1`
    FOREIGN KEY (`idGroupe` )
    REFERENCES `croixrouge`.`Groupes` (`idGroupe` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PossederDroit_Droits1`
    FOREIGN KEY (`idDroit` )
    REFERENCES `croixrouge`.`Droits` (`idDroit` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Equipements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Equipements` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Equipements` (
  `idEquipement` INT NOT NULL AUTO_INCREMENT ,
  `nom` VARCHAR(255) NULL ,
  `coutPoint` INT(4) NULL ,
  PRIMARY KEY (`idEquipement`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Materiel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Materiel` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Materiel` (
  `idMateriel` INT NOT NULL AUTO_INCREMENT ,
  `nomMateriel` VARCHAR(255) NULL ,
  `dureePeremption` INT(4) NULL ,
  PRIMARY KEY (`idMateriel`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Sacs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Sacs` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Sacs` (
  `idSac` INT NOT NULL AUTO_INCREMENT ,
  `numeroSac` VARCHAR(255) NULL ,
  PRIMARY KEY (`idSac`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`Ranger`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`Ranger` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`Ranger` (
  `idSac` INT NOT NULL ,
  `idMateriel` INT NULL ,
  `dateAcquisition` DATETIME NULL ,
  PRIMARY KEY (`idSac`) ,
  INDEX `fk_Ranger_Materiel1_idx` (`idMateriel` ASC) ,
  CONSTRAINT `fk_Ranger_Sacs1`
    FOREIGN KEY (`idSac` )
    REFERENCES `croixrouge`.`Sacs` (`idSac` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ranger_Materiel1`
    FOREIGN KEY (`idMateriel` )
    REFERENCES `croixrouge`.`Materiel` (`idMateriel` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`FormationsSuivie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`FormationsSuivie` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`FormationsSuivie` (
  `matricule` VARCHAR(255) NOT NULL ,
  `idFormation` INT NOT NULL ,
  PRIMARY KEY (`matricule`, `idFormation`) ,
  INDEX `fk_FormationsSuivie_Formation1_idx` (`idFormation` ASC) ,
  CONSTRAINT `fk_FormationsSuivie_Volontaires1`
    FOREIGN KEY (`matricule` )
    REFERENCES `croixrouge`.`Volontaires` (`matricule` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FormationsSuivie_Formation1`
    FOREIGN KEY (`idFormation` )
    REFERENCES `croixrouge`.`Formation` (`idFormation` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`GrilleHoraire`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`GrilleHoraire` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`GrilleHoraire` (
  `dateCréation` DATETIME NULL ,
  `dateModification` DATETIME NULL ,
  `numéroSemaine` INT NOT NULL ,
  `dateDebut` DATETIME NULL ,
  `dateFin` DATETIME NULL ,
  `ambulance` VARCHAR(100) NOT NULL ,
  `lieu` VARCHAR(100) NOT NULL ,
  `idGrilleHoraire` INT NULL AUTO_INCREMENT ,
  `locked` INT NOT NULL DEFAULT 0 ,
  `année` INT NOT NULL ,
  PRIMARY KEY (`numéroSemaine`, `ambulance`, `lieu`, `année`) ,
  UNIQUE INDEX `idGrilleHoraire_UNIQUE` (`idGrilleHoraire` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`CaseHoraire`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`CaseHoraire` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`CaseHoraire` (
  `date` DATETIME NOT NULL ,
  `heurePrestation` VARCHAR(100) NOT NULL ,
  `role` VARCHAR(100) NOT NULL ,
  `idCaseHoraire` INT NULL AUTO_INCREMENT ,
  `numrow` INT NULL ,
  `numcolumn` INT NULL ,
  `jour` VARCHAR(100) NULL ,
  PRIMARY KEY (`date`, `heurePrestation`, `role`) ,
  UNIQUE INDEX `idCaseHoraire_UNIQUE` (`idCaseHoraire` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `croixrouge`.`AssignationCaseHoraire`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `croixrouge`.`AssignationCaseHoraire` ;

CREATE  TABLE IF NOT EXISTS `croixrouge`.`AssignationCaseHoraire` (
  `matricule` VARCHAR(255) NOT NULL ,
  `idCaseHoraire` INT NOT NULL ,
  PRIMARY KEY (`matricule`, `idCaseHoraire`) ,
  INDEX `fk_AssignationCaseHoraire_CaseHoraire1_idx` (`idCaseHoraire` ASC) ,
  INDEX `fk_AssignationCaseHoraire_Volontaires2_idx` (`matricule` ASC) ,
  CONSTRAINT `fk_AssignationCaseHoraire_CaseHoraire1`
    FOREIGN KEY (`idCaseHoraire` )
    REFERENCES `croixrouge`.`CaseHoraire` (`idCaseHoraire` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AssignationCaseHoraire_Volontaires2`
    FOREIGN KEY (`matricule` )
    REFERENCES `croixrouge`.`Volontaires` (`matricule` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AssignationCaseHoraire_Volontaires1`
    FOREIGN KEY (`matricule` )
    REFERENCES `croixrouge`.`Volontaires` (`matricule` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
