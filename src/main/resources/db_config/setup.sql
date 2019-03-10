-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2019 at 04:42 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `due_date` date NOT NULL,
  `patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`id`, `status`, `due_date`, `patient_id`) VALUES
(1, 0, '2019-03-02', 1),
(2, 0, '2019-03-02', 2),
(3, 0, '2019-03-02', 3),
(4, 0, '2019-03-02', 4),
(5, 0, '2019-03-02', 5),
(6, 0, '2019-11-10', 6),
(7, 0, '2019-11-10', 7),
(8, 0, '2019-03-05', 8),
(9, 0, '2019-03-05', 9),
(10, 0, '2019-03-20', 10),
(11, 0, '2019-03-20', 11),
(12, 0, '2019-03-20', 12),
(13, 0, '2019-03-21', 13),
(14, 0, '2019-03-29', 14),
(15, 0, '2019-03-30', 15),
(16, 0, '2019-03-29', 16),
(17, 0, '2019-04-05', 17);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `vnumber` varchar(30) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `sname` varchar(30) NOT NULL,
  `dob` date NOT NULL,
  `local_clinic` varchar(30) NOT NULL,
  `next_appointment` date NOT NULL,
  `refresh_rate` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `vnumber`, `fname`, `sname`, `dob`, `local_clinic`, `next_appointment`, `refresh_rate`) VALUES
(1, '1381274', 'Mara', 'Zimbler', '1997-12-20', 'Spitalul Local Craiova', '2019-03-02', 2),
(3, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(4, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(5, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(6, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(7, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(8, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(9, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(10, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(11, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(12, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'cluj', '2019-03-02', 2),
(13, 'v1234', 'Tudor', 'Iures', '1998-01-01', 'timisoara', '2019-03-02', 2),
(14, 'v123', 'vlad', 'm', '1998-11-10', 'spit_buc', '2019-11-10', 1),
(15, 'v123', 'vlad', 'm', '1998-11-10', 'spit_buc', '2019-11-10', 1),
(16, 'dada', 'dada', 'dada', '2019-03-04', 'dada', '2019-03-05', 1),
(17, 'dada', 'dada', 'dada', '2019-03-04', 'dada', '2019-03-05', 1),
(18, 'dada', 'dada', 'dada', '2019-03-20', 'dada', '2019-03-20', 1),
(19, 'dada', 'dada', 'dada', '2019-03-27', 'dada', '2019-03-20', 1),
(20, 'dada', 'Vlaaaaaad', 'dada', '2019-03-27', 'dada', '2019-03-20', 1),
(21, 'dada', 'dadafafafa', 'dada', '2019-03-14', 'dada', '2019-03-21', 1),
(22, 'asdadd', 'dadasdad', 'asdadsasd', '2019-03-23', 'saaaaaaaaaaaaaaaaaaaaaaaaaa', '2019-03-29', 1),
(23, 'adsda', 'fha', 'adhc', '2019-03-21', 'dadsa', '2019-03-30', 1),
(24, '4123', 'Grigore', 'Ureche', '2008-03-12', 'Galati Hospital', '2019-03-29', 1),
(25, 'v12843719', 'Tom', 'Harris', '1995-03-29', 'London St Thomas', '2019-04-05', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_admin` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`, `is_admin`) VALUES
(0, 'hpavel', 'horiapavel@yahoo.com', '20000:a3bba72c29599f0bec1608e59d6f784cf784673c03c4923e:a351d06b6457cc44f6156b5ad410366088e556c4bcbe5646', 0),
(1, 'tciure', 'tciures31@gmail.com', '20000:5d251b7b471c1533b85a4083e84731c0cb5c664691a968fc:2a22bd498110dccf8a31dc5c6b65dc914d7484275533e03c', 0),
(2, 'test', 'test@test.com', '20000:178adee7923fbe8910887cce25e875fac2252f10ef8535cf:c553e1054ae7a3009fa647b37ca4741c64da43040d406592', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_patient_id` (`patient_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
