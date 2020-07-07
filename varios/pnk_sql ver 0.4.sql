-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2020 a las 20:17:37
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.9

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acceso`
--

CREATE TABLE `acceso` (
  `ID_ACCESO` int(11) NOT NULL,
  `FECHA_ACCESO` date NOT NULL,
  `HORA_ACCESO` time NOT NULL,
  `TIPO_ACCESO` int(11) NOT NULL,
  `ESTADO` int(11) NOT NULL,
  `ID_CUENTA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `ID_CUENTA` int(11) NOT NULL,
  `CLAVE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `ESTADO` int(11) NOT NULL,
  `FOTO` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `ID_PERSONA` int(11) NOT NULL,
  `UID` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `ID_DIRECCION` int(11) NOT NULL,
  `PISO` int(11) NOT NULL,
  `BLOCK` int(11) NOT NULL,
  `NUMERO` int(11) NOT NULL,
  `ID_PERSONA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encomienda`
--

CREATE TABLE `encomienda` (
  `ID_ENCOMIENDA` int(11) NOT NULL,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `DESCRIPCION` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `ESTADO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `ID_PERSONA` int(11) NOT NULL,
  `RUT` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `SEG_NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `APE_PATERNO` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `APE_MATERNO` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `TELEFONO` int(11) NOT NULL,
  `EMAIL` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `ESTADO` int(11) NOT NULL,
  `ID_TIPO_PERSONA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud_encomienda`
--

CREATE TABLE `solicitud_encomienda` (
  `ID_SOLICITUD_ENCOMIENDA` int(11) NOT NULL,
  `ID_CUENTA` int(11) NOT NULL,
  `ESTADO` int(11) NOT NULL,
  `ID_ENCOMIENDA` int(11) NOT NULL,
  `FECHA_ENTREGA` date NOT NULL,
  `HORA_ENTREGA` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud_visita`
--

CREATE TABLE `solicitud_visita` (
  `ID_SOLICITUD_VISITA` int(11) NOT NULL,
  `ID_CUENTA` int(11) NOT NULL,
  `FECHA_VISITA` date NOT NULL,
  `ID_PERSONA` int(11) NOT NULL,
  `HORA_VISITA` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta_nfc`
--

CREATE TABLE `tarjeta_nfc` (
  `UID` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `ESTADO` int(11) NOT NULL,
  `FECHA_REGISTRO` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_persona`
--

CREATE TABLE `tipo_persona` (
  `ID_TIPO_PERSONA` int(11) NOT NULL,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `DESCRIPCION` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `acceso`
--
ALTER TABLE `acceso`
  ADD PRIMARY KEY (`ID_ACCESO`),
  ADD KEY `ID_CUENTA` (`ID_CUENTA`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`ID_CUENTA`),
  ADD KEY `ID_PERSONA` (`ID_PERSONA`),
  ADD KEY `UID` (`UID`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`ID_DIRECCION`),
  ADD KEY `ID_PERSONA` (`ID_PERSONA`);

--
-- Indices de la tabla `encomienda`
--
ALTER TABLE `encomienda`
  ADD PRIMARY KEY (`ID_ENCOMIENDA`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`ID_PERSONA`),
  ADD KEY `ID_TIPO_PERSONA` (`ID_TIPO_PERSONA`);

--
-- Indices de la tabla `solicitud_encomienda`
--
ALTER TABLE `solicitud_encomienda`
  ADD PRIMARY KEY (`ID_SOLICITUD_ENCOMIENDA`,`ID_ENCOMIENDA`,`ID_CUENTA`),
  ADD KEY `ID_ENCOMIENDA` (`ID_ENCOMIENDA`),
  ADD KEY `ID_CUENTA` (`ID_CUENTA`);

--
-- Indices de la tabla `solicitud_visita`
--
ALTER TABLE `solicitud_visita`
  ADD PRIMARY KEY (`ID_SOLICITUD_VISITA`,`ID_PERSONA`,`ID_CUENTA`),
  ADD KEY `ID_CUENTA` (`ID_CUENTA`),
  ADD KEY `ID_PERSONA` (`ID_PERSONA`);

--
-- Indices de la tabla `tarjeta_nfc`
--
ALTER TABLE `tarjeta_nfc`
  ADD PRIMARY KEY (`UID`);

--
-- Indices de la tabla `tipo_persona`
--
ALTER TABLE `tipo_persona`
  ADD PRIMARY KEY (`ID_TIPO_PERSONA`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `acceso`
--
ALTER TABLE `acceso`
  MODIFY `ID_ACCESO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `ID_CUENTA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `ID_DIRECCION` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `encomienda`
--
ALTER TABLE `encomienda`
  MODIFY `ID_ENCOMIENDA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `ID_PERSONA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitud_encomienda`
--
ALTER TABLE `solicitud_encomienda`
  MODIFY `ID_SOLICITUD_ENCOMIENDA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitud_visita`
--
ALTER TABLE `solicitud_visita`
  MODIFY `ID_SOLICITUD_VISITA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_persona`
--
ALTER TABLE `tipo_persona`
  MODIFY `ID_TIPO_PERSONA` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `acceso`
--
ALTER TABLE `acceso`
  ADD CONSTRAINT `acceso_ibfk_1` FOREIGN KEY (`ID_CUENTA`) REFERENCES `cuenta` (`ID_CUENTA`);

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`ID_PERSONA`) REFERENCES `persona` (`ID_PERSONA`),
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`UID`) REFERENCES `tarjeta_nfc` (`UID`);

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`ID_PERSONA`) REFERENCES `persona` (`ID_PERSONA`);

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`ID_TIPO_PERSONA`) REFERENCES `tipo_persona` (`ID_TIPO_PERSONA`);

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
