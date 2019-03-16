-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2019 at 01:18 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

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
(1, 0, '2019-03-02', 9),
(2, 0, '2019-03-02', 10),
(3, 0, '2019-03-02', 11),
(4, 0, '2019-03-02', 12),
(5, 0, '2019-03-02', 13),
(7, 0, '2019-11-10', 15),
(8, 0, '2019-03-05', 16),
(9, 0, '2019-03-05', 17),
(10, 0, '2019-03-20', 18),
(11, 0, '2019-03-20', 19),
(12, 0, '2019-03-20', 20),
(13, 0, '2019-03-21', 21),
(14, 0, '2019-03-29', 22),
(15, 0, '2019-03-30', 23),
(16, 0, '2019-03-29', 24),
(17, 0, '2019-04-05', 25),
(19, 0, '2019-03-30', 27),
(20, 0, '2019-03-30', 28),
(21, 0, '2019-03-30', 29),
(25, 0, '2019-03-04', 14),
(26, 0, '2019-03-14', 26),
(27, 0, '2019-03-15', 1),
(31, 0, '2019-03-19', 30),
(32, 0, '2019-03-18', 31),
(33, 0, '2019-03-18', 32),
(34, 0, '2019-03-19', 33);

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `forename` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `relationship` varchar(100) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`id`, `forename`, `surname`, `relationship`, `phone`, `email`, `patient_id`) VALUES
(1, 'George', 'Huntington', 'Close realtionship: Father', '+447733974322', 'george.huntington@gmail.com', 26),
(3, 'Bob', 'Andersen', 'Uncle', '+425423542', 'bob.anders@gmail.com', 26),
(7, 'Joe', 'Midsummer', 'Uncle', '+4252352342', 'joe@yahoo.com', 24),
(10, 'Gorun', 'Surpriza', 'Unchi', '+4182381', '1Q.sapro@gmail.com', 1),
(15, 'dasd', 'adad', 'asdad', 'sadad', 'asda', 1),
(16, 'dasdada', 'dsadsasd', 'adasd', 'asdasdasd', 'asdads', 1),
(17, 'asdadsa', 'dasdasd', 'adsassda', 'sdasdasd', 'adada', 1),
(18, 'Alicia', 'Buffer', 'Mother', '+448833982123', 'alicia.buffer@gmail.com', 26);

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
  `diagnosis` varchar(50) DEFAULT 'N/A',
  `local_clinic` varchar(30) NOT NULL,
  `refresh_rate` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `vnumber`, `fname`, `sname`, `dob`, `diagnosis`, `local_clinic`, `refresh_rate`) VALUES
(1, 'B444512', 'Yinuo', 'KCL', '2011-03-24', 'N/A', 'Lancaster Hospital', 2),
(3, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(4, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(5, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(6, 'v1234', 'Horia', 'Pavelsdayd', '1998-01-01', 'N/A', 'cluj', 2),
(7, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(8, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(9, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(10, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(11, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(12, 'v1234', 'Horia', 'Pavel', '1998-01-01', 'N/A', 'cluj', 2),
(13, 'v1234', 'Tudor', 'Iures', '1998-01-01', 'N/A', 'timisoara', 2),
(14, 'v123', 'vlad', 'm', '1998-11-10', 'N/A', 'spit_buc', 1),
(15, 'v123', 'vlad', 'm', '1998-11-10', 'N/A', 'spit_buc', 1),
(16, 'dada', 'dada', 'dada', '2019-03-04', 'N/A', 'dada', 1),
(17, 'dada', 'dada', 'dada', '2019-03-04', 'N/A', 'dada', 1),
(18, 'dada', 'dada', 'dada', '2019-03-20', 'N/A', 'dada', 1),
(19, 'dada', 'dada', 'dada', '2019-03-27', 'N/A', 'dada', 1),
(20, 'dada', 'Vlaaaaaad', 'dada', '2019-03-27', 'N/A', 'dada', 1),
(21, 'dada', 'dadafafafa', 'dada', '2019-03-14', 'N/A', 'dada', 1),
(22, 'asdadd', 'dadasdad', 'asdadsasd', '2019-03-23', 'N/A', 'saaaaaaaaaaaaaaaaaaaaaaaaaa', 1),
(23, 'adsda', 'fha', 'adhc', '2019-03-21', 'N/A', 'dadsa', 1),
(24, '4123', 'Grigore', 'Ureche', '2008-03-12', 'N/A', 'Galati Hospital', 1),
(25, 'v12843719', 'Thomas Filipo', 'Harris', '1995-03-29', 'N/A', 'London St Thomas', 1),
(26, '552231', 'Clara', 'Fielsson', '2012-03-19', 'N/A', 'Gordbach Local Hospital Y', 1),
(27, 'A444123', 'Yinuo', 'KCL', '2019-03-12', 'N/A', 'London, St Thomas', 1),
(28, 'A444123', 'Yinuo', 'KCL', '2019-03-12', 'N/A', 'London, St Thomas', 1),
(29, 'khg', 'Yinuo', 'KCL', '2019-03-19', 'N/A', 'lkj', 1),
(30, 'A123456', 'Ion', 'Ionescu', '2004-03-23', 'N/A', 'Spitalul Municipal Galati', 1),
(31, 'V123141', 'Birger', 'Vanger', '1939-03-16', 'N/A', 'Hedestad Local Hospital', 2),
(32, 'H123456', 'Cecilla', 'Vanger', '1994-03-16', 'N/A', 'Hedestad Local Hospital', 2),
(33, 'H999998', 'Michael', 'Blomkvist', '1994-03-24', 'Very Good Journalist', 'Hedestad Local Hospital', 2);

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
(1, 'hpavel', 'horiapavel@yahoo.com', '20000:a3bba72c29599f0bec1608e59d6f784cf784673c03c4923e:a351d06b6457cc44f6156b5ad410366088e556c4bcbe5646', 0),
(2, 'tciure', 'tciures31@gmail.com', '20000:5d251b7b471c1533b85a4083e84731c0cb5c664691a968fc:2a22bd498110dccf8a31dc5c6b65dc914d7484275533e03c', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `patient_id` (`patient_id`);

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_contact_id` (`patient_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

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
  ADD CONSTRAINT `fk_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `fk_contact_id` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
