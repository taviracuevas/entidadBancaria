-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.13 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             8.0.0.4530
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para banco
DROP DATABASE IF EXISTS `banco`;
CREATE DATABASE IF NOT EXISTS `banco` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banco`;


-- Volcando estructura para tabla banco.cuenta_bancaria
DROP TABLE IF EXISTS `cuenta_bancaria`;
CREATE TABLE IF NOT EXISTS `cuenta_bancaria` (
  `idCuenta_Bancaria` int(11) NOT NULL,
  `id_SucursalBancaria` int(11) DEFAULT NULL,
  `numeroDeCuenta` varchar(45) DEFAULT NULL,
  `cif` varchar(45) DEFAULT NULL,
  `dc` varchar(45) DEFAULT NULL,
  `saldo` decimal(40,2) DEFAULT NULL,
  PRIMARY KEY (`idCuenta_Bancaria`),
  KEY `id_sucursal_idx` (`id_SucursalBancaria`),
  CONSTRAINT `id_sucursal` FOREIGN KEY (`id_SucursalBancaria`) REFERENCES `sucursal` (`idSucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.cuenta_bancaria: ~0 rows (aproximadamente)
DELETE FROM `cuenta_bancaria`;
/*!40000 ALTER TABLE `cuenta_bancaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_bancaria` ENABLE KEYS */;


-- Volcando estructura para tabla banco.entidad_bancaria
DROP TABLE IF EXISTS `entidad_bancaria`;
CREATE TABLE IF NOT EXISTS `entidad_bancaria` (
  `idEntidad` int(11) NOT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `cif` varchar(45) DEFAULT NULL,
  `tipoEntidadBancaria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEntidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.entidad_bancaria: ~0 rows (aproximadamente)
DELETE FROM `entidad_bancaria`;
/*!40000 ALTER TABLE `entidad_bancaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidad_bancaria` ENABLE KEYS */;


-- Volcando estructura para tabla banco.movimiento
DROP TABLE IF EXISTS `movimiento`;
CREATE TABLE IF NOT EXISTS `movimiento` (
  `idMovimiento` int(11) NOT NULL,
  `tipoDeMovimiento` varchar(45) DEFAULT NULL,
  `importe` decimal(40,2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `saldo` decimal(40,2) DEFAULT NULL,
  `concepto` varchar(45) DEFAULT NULL,
  `idCuentaBancaria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMovimiento`),
  KEY `idCuenta_idx` (`idCuentaBancaria`),
  CONSTRAINT `idCuenta` FOREIGN KEY (`idCuentaBancaria`) REFERENCES `cuenta_bancaria` (`idCuenta_Bancaria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.movimiento: ~0 rows (aproximadamente)
DELETE FROM `movimiento`;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;


-- Volcando estructura para tabla banco.sucursal
DROP TABLE IF EXISTS `sucursal`;
CREATE TABLE IF NOT EXISTS `sucursal` (
  `idSucursal` int(11) NOT NULL,
  `id_entidadBancaria` int(11) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSucursal`),
  KEY `id_entidadBancaria_idx` (`id_entidadBancaria`),
  CONSTRAINT `id_entidadBancaria` FOREIGN KEY (`id_entidadBancaria`) REFERENCES `entidad_bancaria` (`idEntidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.sucursal: ~0 rows (aproximadamente)
DELETE FROM `sucursal`;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
