-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 07-07-2020 a las 03:47:28
-- Versión del servidor: 10.4.10-MariaDB-log
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pnk`
--
CREATE DATABASE IF NOT EXISTS `pnk` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `pnk`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acceso`
--

DROP TABLE IF EXISTS `acceso`;
CREATE TABLE IF NOT EXISTS `acceso` (
  `ID_ACCESO` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA_ACCESO` date NOT NULL,
  `HORA_ACCESO` time NOT NULL,
  `TIPO_ACCESO` int(11) NOT NULL,
  `ESTADO_ACCESO` int(11) NOT NULL,
  `ID_CUENTA` int(11) NOT NULL,
  `ID_BARRERA` int(11) NOT NULL,
  PRIMARY KEY (`ID_ACCESO`),
  KEY `acceso_ibfk_1` (`ID_CUENTA`),
  KEY `acceso_ibfk_2` (`ID_BARRERA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `barrera`
--

DROP TABLE IF EXISTS `barrera`;
CREATE TABLE IF NOT EXISTS `barrera` (
  `ID_BARRERA` int(11) NOT NULL AUTO_INCREMENT,
  `BARRERA_ESTADO` int(11) NOT NULL,
  `DESCRIPCION` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `LATITUD` float NOT NULL,
  `LONGITUD` float NOT NULL,
  PRIMARY KEY (`ID_BARRERA`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
CREATE TABLE IF NOT EXISTS `cuenta` (
  `ID_CUENTA` int(11) NOT NULL AUTO_INCREMENT,
  `CLAVE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `ESTADO_CUENTA` int(11) NOT NULL,
  `FOTO` mediumblob NOT NULL,
  `ID_PERSONA` int(11) NOT NULL,
  `UID` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`ID_CUENTA`),
  KEY `cuenta_ibfk_1` (`ID_PERSONA`),
  KEY `cuenta_ibfk_2` (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

DROP TABLE IF EXISTS `direccion`;
CREATE TABLE IF NOT EXISTS `direccion` (
  `ID_DIRECCION` int(11) NOT NULL AUTO_INCREMENT,
  `PISO` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `BLOCK` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `NUMERO` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`ID_DIRECCION`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion_persona`
--

DROP TABLE IF EXISTS `direccion_persona`;
CREATE TABLE IF NOT EXISTS `direccion_persona` (
  `direccion_ID_DIRECCION` int(11) NOT NULL,
  `persona_ID_PERSONA` int(11) NOT NULL,
  PRIMARY KEY (`direccion_ID_DIRECCION`,`persona_ID_PERSONA`),
  KEY `fk_direccion_has_persona_persona1` (`persona_ID_PERSONA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encomienda`
--

DROP TABLE IF EXISTS `encomienda`;
CREATE TABLE IF NOT EXISTS `encomienda` (
  `ID_ENCOMIENDA` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `DESCRIPCION` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `ESTADO_ENCOMIENDA` int(11) NOT NULL,
  PRIMARY KEY (`ID_ENCOMIENDA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `ID_PERSONA` int(11) NOT NULL AUTO_INCREMENT,
  `RUT` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `SEG_NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `APE_PATERNO` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `APE_MATERNO` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `TELEFONO` varchar(11) COLLATE utf8_spanish_ci DEFAULT NULL,
  `EMAIL` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `ESTADO_PERSONA` int(11) NOT NULL,
  `ID_TIPO_PERSONA` int(11) NOT NULL,
  PRIMARY KEY (`ID_PERSONA`),
  KEY `fk_persona_tipo_persona1` (`ID_TIPO_PERSONA`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesion`
--

DROP TABLE IF EXISTS `sesion`;
CREATE TABLE IF NOT EXISTS `sesion` (
  `ID_SESION` int(11) NOT NULL AUTO_INCREMENT,
  `TOKEN` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `DISPOSITIVO` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `DIRECCION_IP` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `FECHA_SESION` date NOT NULL,
  `HORA_SESION` time NOT NULL,
  `ID_CUENTA` int(11) NOT NULL,
  PRIMARY KEY (`ID_SESION`),
  KEY `sesion_ibfk_1` (`ID_CUENTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud_encomienda`
--

DROP TABLE IF EXISTS `solicitud_encomienda`;
CREATE TABLE IF NOT EXISTS `solicitud_encomienda` (
  `ID_SOLICITUD_ENCOMIENDA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CUENTA` int(11) NOT NULL,
  `ESTADO_SOLICITUD_ENCOMIENDA` int(11) NOT NULL,
  `ID_ENCOMIENDA` int(11) NOT NULL,
  `FECHA_ENTREGA` date NOT NULL,
  `HORA_ENTREGA` time NOT NULL,
  PRIMARY KEY (`ID_SOLICITUD_ENCOMIENDA`,`ID_ENCOMIENDA`,`ID_CUENTA`),
  KEY `solicitud_encomienda_ibfk_1` (`ID_ENCOMIENDA`),
  KEY `solicitud_encomienda_ibfk_2` (`ID_CUENTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud_visita`
--

DROP TABLE IF EXISTS `solicitud_visita`;
CREATE TABLE IF NOT EXISTS `solicitud_visita` (
  `ID_SOLICITUD_VISITA` int(11) NOT NULL AUTO_INCREMENT,
  `ID_CUENTA` int(11) NOT NULL,
  `ESTADO_SOLICITUD_VISITA` int(11) NOT NULL,
  `FECHA_VISITA` date NOT NULL,
  `ID_PERSONA` int(11) NOT NULL,
  `HORA_VISITA` time NOT NULL,
  PRIMARY KEY (`ID_SOLICITUD_VISITA`,`ID_PERSONA`,`ID_CUENTA`),
  KEY `solicitud_visita_ibfk_1` (`ID_CUENTA`),
  KEY `solicitud_visita_ibfk_2` (`ID_PERSONA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta_nfc`
--

DROP TABLE IF EXISTS `tarjeta_nfc`;
CREATE TABLE IF NOT EXISTS `tarjeta_nfc` (
  `UID` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `ESTADO_TARJETA_NFC` int(11) NOT NULL,
  `FECHA_REGISTRO` date NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tarjeta_nfc`
--

INSERT INTO `tarjeta_nfc` VALUES('0', 1, '2020-06-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_persona`
--

DROP TABLE IF EXISTS `tipo_persona`;
CREATE TABLE IF NOT EXISTS `tipo_persona` (
  `ID_TIPO_PERSONA` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `DESCRIPCION` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`ID_TIPO_PERSONA`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_persona`
--

INSERT INTO `tipo_persona` VALUES(1, 'Portero', 'Persona que administra el acceso');
INSERT INTO `tipo_persona` VALUES(2, 'Residente', 'Persona que utiliza el acceso');
INSERT INTO `tipo_persona` VALUES(3, 'Visitante', 'Persona que visita a un residente');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `acceso`
--
ALTER TABLE `acceso`
  ADD CONSTRAINT `acceso_ibfk_1` FOREIGN KEY (`ID_CUENTA`) REFERENCES `cuenta` (`ID_CUENTA`),
  ADD CONSTRAINT `acceso_ibfk_2` FOREIGN KEY (`ID_BARRERA`) REFERENCES `barrera` (`ID_BARRERA`);

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`ID_PERSONA`) REFERENCES `persona` (`ID_PERSONA`),
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`UID`) REFERENCES `tarjeta_nfc` (`UID`);

--
-- Filtros para la tabla `direccion_persona`
--
ALTER TABLE `direccion_persona`
  ADD CONSTRAINT `fk_direccion_has_persona_direccion1` FOREIGN KEY (`direccion_ID_DIRECCION`) REFERENCES `direccion` (`ID_DIRECCION`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_direccion_has_persona_persona1` FOREIGN KEY (`persona_ID_PERSONA`) REFERENCES `persona` (`ID_PERSONA`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `fk_persona_tipo_persona1` FOREIGN KEY (`ID_TIPO_PERSONA`) REFERENCES `tipo_persona` (`ID_TIPO_PERSONA`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD CONSTRAINT `sesion_ibfk_1` FOREIGN KEY (`ID_CUENTA`) REFERENCES `cuenta` (`ID_CUENTA`);

--
-- Filtros para la tabla `solicitud_encomienda`
--
ALTER TABLE `solicitud_encomienda`
  ADD CONSTRAINT `solicitud_encomienda_ibfk_1` FOREIGN KEY (`ID_ENCOMIENDA`) REFERENCES `encomienda` (`ID_ENCOMIENDA`),
  ADD CONSTRAINT `solicitud_encomienda_ibfk_2` FOREIGN KEY (`ID_CUENTA`) REFERENCES `cuenta` (`ID_CUENTA`);

--
-- Filtros para la tabla `solicitud_visita`
--
ALTER TABLE `solicitud_visita`
  ADD CONSTRAINT `solicitud_visita_ibfk_1` FOREIGN KEY (`ID_CUENTA`) REFERENCES `cuenta` (`ID_CUENTA`),
  ADD CONSTRAINT `solicitud_visita_ibfk_2` FOREIGN KEY (`ID_PERSONA`) REFERENCES `persona` (`ID_PERSONA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
