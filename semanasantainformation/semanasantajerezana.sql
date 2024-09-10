-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 31-08-2024 a las 11:33:32
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `semanasantajerezana`
--
CREATE DATABASE IF NOT EXISTS `semanasantajerezana` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `semanasantajerezana`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `banda`
--

DROP TABLE IF EXISTS `banda`;
CREATE TABLE `banda` (
  `id` bigint(20) NOT NULL,
  `estilo` tinyint(4) NOT NULL,
  `fundacion` date NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `numero_componentes_aprox` int(11) NOT NULL,
  `ciudad_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `banda`
--

INSERT INTO `banda` (`id`, `estilo`, `fundacion`, `nombre`, `numero_componentes_aprox`, `ciudad_id`) VALUES
(1, 0, '2024-08-08', 'La Sentencia', 100, 1),
(3, 0, '2024-08-16', 'San Juan', 100, 1),
(4, 0, '2024-08-16', 'La Clemencia', 100, 1),
(5, 2, '2024-08-22', 'Maestro Enrique Galán', 70, 2),
(6, 1, '2023-08-08', 'Vera-Cruz', 100, 4),
(7, 1, '2024-08-23', 'La Caridad', 100, 1),
(17, 1, '2024-08-23', 'La Prueba', 100, 1),
(18, 1, '2024-08-27', 'Nuestra Señora de Valme', 100, 5),
(23, 1, '2024-08-27', 'Fé y Consuelo', 100, 6),
(24, 1, '2024-08-27', 'Nuestra Señora del Rosario Coronada', 100, 7),
(25, 1, '2024-08-27', 'Santísimo Cristo de los Remedios', 100, 8),
(26, 2, '2024-08-27', 'Virgen del Castillo', 100, 9),
(27, 1, '2024-08-27', 'Coronación', 100, 10),
(28, 1, '2024-08-27', 'Nazareno', 100, 12),
(29, 2, '2024-08-27', 'Nazareno', 100, 13),
(30, 6, '2024-08-27', 'Via Sacra', 5, 11),
(31, 6, '2024-08-27', 'San Pedro Nolasco', 5, 1),
(32, 1, '2024-08-27', 'Asociación Ruiz-Mateos', 100, 2),
(33, 1, '2024-08-27', 'Presentación al Pueblo', 100, 5),
(34, 1, '2024-08-27', 'Centuria Macarena', 100, 3),
(35, 2, '2024-08-27', 'Santa Ana', 100, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
CREATE TABLE `ciudad` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `provincia_id` bigint(20) DEFAULT NULL,
  `comunidad_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id`, `nombre`, `provincia_id`, `comunidad_id`) VALUES
(1, 'Jerez de la Frontera', 1, NULL),
(2, 'Rota', 1, NULL),
(3, 'Sevilla', 8, NULL),
(4, 'Los Palacios y Villafranca', 8, NULL),
(5, 'Dos Hermanas', 8, NULL),
(6, 'Martos', 6, NULL),
(7, 'Cádiz', 1, NULL),
(8, 'Castilleja de la Cuesta', 8, NULL),
(9, 'Lebrija', 8, NULL),
(10, 'Campillos', 5, NULL),
(11, 'El Puerto de Santa María', 1, NULL),
(12, 'Utrera', 8, NULL),
(13, 'Rota', 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comunidad_autonoma`
--

DROP TABLE IF EXISTS `comunidad_autonoma`;
CREATE TABLE `comunidad_autonoma` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comunidad_autonoma`
--

INSERT INTO `comunidad_autonoma` (`id`, `nombre`) VALUES
(1, 'Andalucía'),
(2, 'Extremadura'),
(3, 'Canarias'),
(4, 'Cantabria'),
(5, 'Cataluña'),
(6, 'Galicia'),
(7, 'Islas Baleares'),
(8, 'La Rioja'),
(9, 'Navarra'),
(10, 'País Vasco'),
(11, 'Aragón'),
(12, 'Castilla-La Mancha'),
(13, 'Castilla y León'),
(14, 'Comunidad de Madrid'),
(15, 'Ceuta'),
(16, 'Melilla');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato`
--

DROP TABLE IF EXISTS `contrato`;
CREATE TABLE `contrato` (
  `id` bigint(20) NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `banda_id` bigint(20) NOT NULL,
  `hermandad_id` bigint(20) NOT NULL,
  `verdadera_fecha_fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `contrato`
--

INSERT INTO `contrato` (`id`, `fecha_fin`, `fecha_inicio`, `banda_id`, `hermandad_id`, `verdadera_fecha_fin`) VALUES
(1, '2028-04-09', '2024-07-14', 1, 12, NULL),
(2, '2024-03-26', '2013-01-01', 3, 5, NULL),
(3, '2025-04-13', '2008-01-01', 5, 1, NULL),
(4, '2025-04-13', '2024-07-06', 6, 1, NULL),
(6, '2025-04-12', '2024-07-12', 18, 47, NULL),
(7, '2025-04-13', '2024-03-24', 23, 2, NULL),
(8, '2025-04-13', '2013-01-01', 26, 3, NULL),
(9, '2025-04-13', '2022-01-01', 25, 3, NULL),
(10, '2025-04-14', '2023-07-20', 27, 4, NULL),
(11, '2026-04-01', '2003-01-01', 1, 6, NULL),
(12, '2025-04-17', '2024-01-01', 28, 7, NULL),
(13, '2025-04-17', '2022-06-27', 29, 7, NULL),
(14, '2025-04-17', '2023-06-06', 30, 7, NULL),
(15, '2025-04-18', '2015-01-01', 31, 9, NULL),
(16, '2025-04-19', '2023-01-01', 32, 10, NULL),
(17, '2024-03-24', '2024-01-01', 34, 13, NULL),
(18, '2024-03-24', '2024-01-01', 33, 13, NULL),
(19, '2025-04-13', '2024-08-11', 35, 13, NULL),
(20, '2024-03-23', '2022-01-01', 24, 48, NULL),
(21, '2024-03-23', '2022-01-01', 23, 13, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hermandad`
--

DROP TABLE IF EXISTS `hermandad`;
CREATE TABLE `hermandad` (
  `id` bigint(20) NOT NULL,
  `apodo` varchar(64) NOT NULL,
  `dia_salida` tinyint(4) NOT NULL,
  `fundacion` date NOT NULL,
  `historia` varchar(255) NOT NULL,
  `nombre` varchar(1024) NOT NULL,
  `numero_hermanos` int(11) NOT NULL,
  `numero_nazarenos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hermandad`
--

INSERT INTO `hermandad` (`id`, `apodo`, `dia_salida`, `fundacion`, `historia`, `nombre`, `numero_hermanos`, `numero_nazarenos`) VALUES
(1, 'La Borriquita', 1, '1949-07-16', 'Lorem ipsum', 'Ilustre y Lasaliana Hermandad y Cofradía de Cristo Rey en su Entrada Triunfal en Jerusalén, Nuestre Señora de la Estrella y San Juan Bautista de La Salle', 1300, 456),
(2, 'La Pasión', 1, '1949-01-01', 'Lorem ipsum', 'Hermandad y Cofradía de Nazarenos de Nuestro Señor de la Pasión, Negaciones y Lágrimas de San Pedro, Angustia de María Madre de la Iglesia y Santa Ángela de la Cruz', 300, 125),
(3, 'El Perdón', 1, '1963-01-01', 'Lorem ipsum', 'Hermandad y Cofradía de Nazarenos Santísimo Cristo del Perdón, Maria Santísima del Perpetuo Socorro y San José Obrero', 425, 174),
(4, 'La Sed', 2, '2013-01-01', 'Lorem Ipsum', 'Hermandad Sacramental de la Santísima Trinidad, Trono de Misericordia y Cofradía de Nazarenos del Santísimo Cristo de la Sed, Amparo de María Santísima y Santa Teresa de Calcuta.', 415, 145),
(5, 'Bondad y Misericordia', 3, '2014-03-20', 'Lorem Ipsum', 'Hospitalaria Hermandad y Cofradía de Nazarenos de Nuestro Señor de la Bondad y Misericordia en el Sagrado Lavatorio de Pies a sus Discípulos, San Juan Grande y San Juan de Dios', 214, 74),
(6, 'Soberano Poder', 4, '2004-12-08', 'Lorem Ipsum', 'Hermandad y Cofradía de Nazarenos de Ntro. Padre Jesús en su Soberano Poder ate Caifás, María Santísima de las Mercedes y San Juan Evangelista.', 650, 270),
(7, 'Vera Cruz', 5, '1542-01-01', 'Lorem Ipsum', 'Real, Ilustre, Antigua y Venerable Hermandad y Cofradía de Nazarenos de la Santa Veracruz de Nuestro Señor Jesucristo, Santísimo Cristo de la Esperanza, Santa María de las Lágrimas y Beato Guillermo José Chaminade.', 600, 397),
(8, 'Santo Crucifijo', 6, '1573-03-24', 'Lorem Ipsum', 'Pontificia, Antigua y Venerable Hermandad del Santísimo Sacramento y Cofradía de Nazarenos del Santo Crucifijo de la Salud y María Santísima de la Encarnación.', 806, 236),
(9, 'El Loreto', 7, '1952-01-01', 'Lorem Ipsum', 'Venerable Hermandad y Cofradía de Nazarenos de la Santa Ceuz en el Monte Calvario y Nuestra Señora de Loreto en su Soledad.', 280, 150),
(10, 'Las Almas', 8, '0001-01-01', 'Lorem Ipsum', 'Ilustrísima, Venerable y Antigua Hermandad y Cofradía del Santísimo Sacramento, Ánimas Benditas y Nuestra Señora de la Paz', 0, 0),
(11, 'El Resucitado', 9, '1999-01-01', 'Lorem Ipsum', 'Hermandad de Sagrada Resurrección de Nuestrro Señor Jesucristo y Nuestra Señora de la Luz.', 250, 33),
(12, 'La Coronación', 1, '1615-07-28', 'Lorem Ipsum', 'Muy Ilustre, Antigua y Venerable Hermandad del Santísimo Cristo de la Coronación de Espinas, María Santísima de la Paz en su Mayor Aflicción y San Juan Bautista.', 550, 272),
(13, 'El Transporte', 1, '1953-01-01', 'Lorem Ipsum', 'Real, Fervorosa y Piadosa Hermandad y Cofradía de Nazarenos de Nuestro Padre Jesús del consuelo en el Desprecio de Herodes, Madre de Dios de la Misericordia y San Cristobal Mártir.', 600, 196),
(14, 'Las Angustias', 1, '1922-01-01', 'Lorem Ipsum', 'Antigua y Venerable Hermandad de Nuestra Señora de las Angustias.', 650, 200),
(44, 'apodo', 1, '2024-05-16', 'lorem_ipsum', 'nombre_de_la_hermandad', 500, 300),
(47, 'Humildad de Barbadillo', 0, '2022-02-19', 'Lorem Ipsum', 'Agrupación Parroquial Sagrado Corazón de Jesús, Ntro. Padre Jesús de la Humildad en su Expolio, Ntra. Sra. del Rocío en sus misterios dolorosos y Beato Leopoldo de Alpandeire.', 201, 201),
(48, 'La Entrega', 0, '2024-08-27', 'Lorem Ipsum', 'Hermandad del Santísimo Sacramento y Cofradía de Nazarenos de Nuestro Señor Jesús Nazareno en su Entrega, María Santísima Reina de los Ángeles y San Francisco de Asís.', 520, 120);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

DROP TABLE IF EXISTS `provincia`;
CREATE TABLE `provincia` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `comunidad_autonoma_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`id`, `nombre`, `comunidad_autonoma_id`) VALUES
(1, 'Cádiz', 1),
(2, 'Badajoz', 2),
(3, 'Almería', 1),
(4, 'Granada', 1),
(5, 'Málaga', 1),
(6, 'Jaén', 1),
(7, 'Córdoba', 1),
(8, 'Sevilla', 1),
(9, 'Cáceres', 2),
(10, 'Santa Cruz de Tenerife', 3),
(11, 'Las Palmas', 3),
(20, 'La Prueba', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salida_procesional`
--

DROP TABLE IF EXISTS `salida_procesional`;
CREATE TABLE `salida_procesional` (
  `id` bigint(20) NOT NULL,
  `clima` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `contrato_id` bigint(20) DEFAULT NULL,
  `hermandad_id` bigint(20) NOT NULL,
  `tipo_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `salida_procesional`
--

INSERT INTO `salida_procesional` (`id`, `clima`, `fecha`, `contrato_id`, `hermandad_id`, `tipo_id`) VALUES
(1, 0, '2023-10-12', 3, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_salida_procesional`
--

DROP TABLE IF EXISTS `tipo_salida_procesional`;
CREATE TABLE `tipo_salida_procesional` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_salida_procesional`
--

INSERT INTO `tipo_salida_procesional` (`id`, `nombre`) VALUES
(2, '50 Aniversario'),
(1, 'Coronación Canónica');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `banda`
--
ALTER TABLE `banda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK769fe98h26ya5yiwg5mcuw6q9` (`ciudad_id`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8pvpi9wl4uebdop190wbj3jyp` (`provincia_id`),
  ADD KEY `FKg7yg9ojrm3xyw8g7jfmn6nwat` (`comunidad_id`);

--
-- Indices de la tabla `comunidad_autonoma`
--
ALTER TABLE `comunidad_autonoma`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKcsfn1pm74otpg4g1ucmydrui5` (`fecha_inicio`,`fecha_fin`,`banda_id`,`hermandad_id`),
  ADD KEY `FKq58sgtsx2nr3xy0xftmjyx0ri` (`banda_id`),
  ADD KEY `FKfelx59r15tu0c9fym7gcr36vl` (`hermandad_id`);

--
-- Indices de la tabla `hermandad`
--
ALTER TABLE `hermandad`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_o5vyj7kmc3gklpw2ilpgtgg3l` (`apodo`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `FKcyr0xlcr2rgvqe6j0bg8rbx1l` (`comunidad_autonoma_id`);

--
-- Indices de la tabla `salida_procesional`
--
ALTER TABLE `salida_procesional`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgxki3cvp51xkkvk7qc8vlvdfh` (`contrato_id`),
  ADD KEY `FKms0phglx84l51oj4ncd9p8hmg` (`hermandad_id`),
  ADD KEY `FKc6km5707sfwk5laot2bw1qwbo` (`tipo_id`);

--
-- Indices de la tabla `tipo_salida_procesional`
--
ALTER TABLE `tipo_salida_procesional`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_eukxsv1x6akjp86ot64wtrqwf` (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `banda`
--
ALTER TABLE `banda`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `comunidad_autonoma`
--
ALTER TABLE `comunidad_autonoma`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `contrato`
--
ALTER TABLE `contrato`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `hermandad`
--
ALTER TABLE `hermandad`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `provincia`
--
ALTER TABLE `provincia`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `salida_procesional`
--
ALTER TABLE `salida_procesional`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tipo_salida_procesional`
--
ALTER TABLE `tipo_salida_procesional`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `banda`
--
ALTER TABLE `banda`
  ADD CONSTRAINT `FK769fe98h26ya5yiwg5mcuw6q9` FOREIGN KEY (`ciudad_id`) REFERENCES `ciudad` (`id`);

--
-- Filtros para la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `FK8pvpi9wl4uebdop190wbj3jyp` FOREIGN KEY (`provincia_id`) REFERENCES `provincia` (`id`),
  ADD CONSTRAINT `FKg7yg9ojrm3xyw8g7jfmn6nwat` FOREIGN KEY (`comunidad_id`) REFERENCES `comunidad_autonoma` (`id`);

--
-- Filtros para la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `FKfelx59r15tu0c9fym7gcr36vl` FOREIGN KEY (`hermandad_id`) REFERENCES `hermandad` (`id`),
  ADD CONSTRAINT `FKq58sgtsx2nr3xy0xftmjyx0ri` FOREIGN KEY (`banda_id`) REFERENCES `banda` (`id`);

--
-- Filtros para la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD CONSTRAINT `FKcyr0xlcr2rgvqe6j0bg8rbx1l` FOREIGN KEY (`comunidad_autonoma_id`) REFERENCES `comunidad_autonoma` (`id`);

--
-- Filtros para la tabla `salida_procesional`
--
ALTER TABLE `salida_procesional`
  ADD CONSTRAINT `FKc6km5707sfwk5laot2bw1qwbo` FOREIGN KEY (`tipo_id`) REFERENCES `tipo_salida_procesional` (`id`),
  ADD CONSTRAINT `FKgxki3cvp51xkkvk7qc8vlvdfh` FOREIGN KEY (`contrato_id`) REFERENCES `contrato` (`id`),
  ADD CONSTRAINT `FKms0phglx84l51oj4ncd9p8hmg` FOREIGN KEY (`hermandad_id`) REFERENCES `hermandad` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;