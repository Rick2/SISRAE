SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `sisrae` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `sisrae` ;

-- -----------------------------------------------------
-- Table `sisrae`.`participante`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`participante` (
  `id_participante` INT NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `apellido_pat` VARCHAR(45) NOT NULL ,
  `apellifo_mat` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id_participante`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`tipo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`tipo` (
  `id_tipo` INT NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `tipocol` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_tipo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`evento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`evento` (
  `id_evento` INT NOT NULL ,
  `tipo_id_tipo` INT NOT NULL ,
  `TipoEvento` VARCHAR(45) NULL ,
  `Descripcion` TEXT NULL ,
  `estatus` VARCHAR(45) NULL ,
  `Duracion` VARCHAR(45) NULL ,
  `comienza_hora` VARCHAR(45) NULL ,
  `termina_hora` VARCHAR(45) NULL ,
  `fecha_inicio` VARCHAR(45) NULL ,
  `fecha_termino` VARCHAR(45) NULL ,
  `obligatorio` TINYINT(1) NULL ,
  PRIMARY KEY (`id_evento`) ,
  INDEX `fk_evento_tipo1` (`tipo_id_tipo` ASC) ,
  CONSTRAINT `fk_evento_tipo1`
    FOREIGN KEY (`tipo_id_tipo` )
    REFERENCES `sisrae`.`tipo` (`id_tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`region`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`region` (
  `id_region` INT NOT NULL ,
  `region` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_region`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`seccion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`seccion` (
  `id_seccion` INT NOT NULL ,
  `region_id_eregion` INT NOT NULL ,
  `seccion` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id_seccion`) ,
  INDEX `fk_seccion_region1` (`region_id_eregion` ASC) ,
  CONSTRAINT `fk_seccion_region1`
    FOREIGN KEY (`region_id_eregion` )
    REFERENCES `sisrae`.`region` (`id_region` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`usuario` (
  `id_usuario` INT NOT NULL ,
  `tipo_usuario` VARCHAR(45) NULL ,
  `nom_usuario` VARCHAR(45) NOT NULL ,
  `psw_usuario` VARCHAR(45) NOT NULL ,
  `h_inicio` VARCHAR(45) NULL ,
  `h_fin` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_usuario`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`encargada`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`encargada` (
  `id_encargada` INT NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `domicilio` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NULL ,
  `telefono` VARCHAR(45) NULL ,
  `sitio_web` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_encargada`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`lugar`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`lugar` (
  `id_lugar` INT NOT NULL ,
  `estado` VARCHAR(45) NULL ,
  `ciudad` VARCHAR(45) NULL ,
  `descripcion` TEXT NULL ,
  `domicilio` VARCHAR(45) NULL ,
  `capacidad` INT NULL ,
  PRIMARY KEY (`id_lugar`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`registro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`registro` (
  `id_participante` INT NOT NULL ,
  `id_evento` INT NOT NULL ,
  `id_registro` INT NOT NULL ,
  `entrada` VARCHAR(45) NULL ,
  `salida` VARCHAR(45) NULL ,
  `calificacion` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_participante`, `id_evento`, `id_registro`) ,
  INDEX `fk_participante_has_evento_evento1` (`id_evento` ASC) ,
  INDEX `fk_participante_has_evento_participante` (`id_participante` ASC) ,
  CONSTRAINT `fk_participante_has_evento_participante`
    FOREIGN KEY (`id_participante` )
    REFERENCES `sisrae`.`participante` (`id_participante` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participante_has_evento_evento1`
    FOREIGN KEY (`id_evento` )
    REFERENCES `sisrae`.`evento` (`id_evento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`atiende`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`atiende` (
  `id_encargada` INT NOT NULL ,
  `id_evento` INT NOT NULL ,
  INDEX `fk_atiende_encargada1` (`id_encargada` ASC) ,
  INDEX `fk_atiende_evento1` (`id_evento` ASC) ,
  CONSTRAINT `fk_atiende_encargada1`
    FOREIGN KEY (`id_encargada` )
    REFERENCES `sisrae`.`encargada` (`id_encargada` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atiende_evento1`
    FOREIGN KEY (`id_evento` )
    REFERENCES `sisrae`.`evento` (`id_evento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisrae`.`sede`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sisrae`.`sede` (
  `evento_id_evento` INT NOT NULL ,
  `lugar_id_lugar` INT NOT NULL ,
  `fecha` VARCHAR(45) NULL ,
  INDEX `fk_sede_evento1` (`evento_id_evento` ASC) ,
  INDEX `fk_sede_lugar1` (`lugar_id_lugar` ASC) ,
  CONSTRAINT `fk_sede_evento1`
    FOREIGN KEY (`evento_id_evento` )
    REFERENCES `sisrae`.`evento` (`id_evento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sede_lugar1`
    FOREIGN KEY (`lugar_id_lugar` )
    REFERENCES `sisrae`.`lugar` (`id_lugar` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
