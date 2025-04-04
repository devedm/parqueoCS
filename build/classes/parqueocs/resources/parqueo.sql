-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2025 at 03:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parqueo`
--

-- --------------------------------------------------------

--
-- Table structure for table `espacioparqueo`
--

CREATE TABLE `espacioparqueo` (
  `id` int(11) NOT NULL,
  `vehiculoPlaca` varchar(10) DEFAULT NULL,
  `parqueoId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `espacioparqueo`
--

INSERT INTO `espacioparqueo` (`id`, `vehiculoPlaca`, `parqueoId`) VALUES
(1, NULL, 1),
(2, NULL, 1),
(3, NULL, 1),
(4, NULL, 1),
(5, NULL, 1),
(6, NULL, 1),
(7, NULL, 1),
(8, NULL, 1),
(9, NULL, 1),
(10, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `parqueo`
--

CREATE TABLE `parqueo` (
  `id` int(11) NOT NULL,
  `nombreParqueo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parqueo`
--

INSERT INTO `parqueo` (`id`, `nombreParqueo`) VALUES
(1, 'Parqueo Equipo 4');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `cedula` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `contrasenia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`cedula`, `nombre`, `contrasenia`) VALUES
(111444777, 'test', '123456789'),
(123456789, 'mario moreno', '123456789');

-- --------------------------------------------------------

--
-- Table structure for table `vehiculo`
--

CREATE TABLE `vehiculo` (
  `placa` varchar(10) NOT NULL,
  `fecha` date DEFAULT NULL,
  `entradaHora` time DEFAULT NULL,
  `salidaHora` time DEFAULT NULL,
  `duracionMinutos` int(11) DEFAULT NULL,
  `cedulaUsuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehiculo`
--

INSERT INTO `vehiculo` (`placa`, `fecha`, `entradaHora`, `salidaHora`, `duracionMinutos`, `cedulaUsuario`) VALUES
('als123', '2025-04-03', '16:48:26', '16:48:26', 0, 123456789),
('brc123', '2025-03-28', '02:03:59', '02:03:59', 0, 123456789);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `espacioparqueo`
--
ALTER TABLE `espacioparqueo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `vehiculoPlaca` (`vehiculoPlaca`),
  ADD KEY `parqueoId` (`parqueoId`);

--
-- Indexes for table `parqueo`
--
ALTER TABLE `parqueo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cedula`);

--
-- Indexes for table `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`placa`),
  ADD KEY `cedulaUsuario` (`cedulaUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `espacioparqueo`
--
ALTER TABLE `espacioparqueo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `parqueo`
--
ALTER TABLE `parqueo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `espacioparqueo`
--
ALTER TABLE `espacioparqueo`
  ADD CONSTRAINT `parqueoId` FOREIGN KEY (`parqueoId`) REFERENCES `parqueo` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `vehiculoPlaca` FOREIGN KEY (`vehiculoPlaca`) REFERENCES `vehiculo` (`placa`) ON DELETE SET NULL;

--
-- Constraints for table `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD CONSTRAINT `cedulaUsuario` FOREIGN KEY (`cedulaUsuario`) REFERENCES `usuario` (`cedula`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
