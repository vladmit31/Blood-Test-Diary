-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2019 at 05:40 PM
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
(38, 0, '2019-03-26', 36),
(39, 0, '2019-04-04', 37),
(40, 0, '2019-04-09', 38),
(41, 0, '2019-03-01', 39),
(42, 0, '2019-05-03', 40),
(43, 0, '2019-04-24', 41),
(44, 0, '2019-05-14', 42),
(45, 0, '2019-04-25', 43),
(46, 0, '2019-04-11', 44),
(47, 0, '2019-04-16', 45),
(48, 0, '2019-03-26', 46),
(49, 0, '2019-05-15', 47),
(50, 0, '2019-05-02', 48),
(51, 0, '2019-04-12', 49),
(52, 0, '2019-04-08', 50),
(53, 0, '2019-05-14', 51),
(54, 0, '2019-05-09', 52),
(55, 0, '2019-03-01', 53),
(56, 0, '2019-03-01', 54),
(57, 0, '2019-04-23', 55);

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
(37, 'Frate', 'Cu', 'Per', '+19381294', 'test_mail987@yahoo.com', 54),
(38, 'Boll', 'Chuckinson', 'Uncle', '+1897438912', 'test_mail987@yahoo.com', 53),
(39, 'Horia', 'Pavel', 'Uncle', '+109840912', 'test_mail987@yahoo.com', 36),
(40, 'Grigorios', 'Loukides', 'Father', '+1891892', 'test_mail987@yahoo.com', 46),
(44, 'Hortensia', 'Pagadag', 'Bengescu', '+128937129', 'test_mail987@yahoo.com', 39);

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
  `refresh_rate` double NOT NULL,
  `lab_name` varchar(30) NOT NULL DEFAULT 'N/A',
  `lab_contact` varchar(30) NOT NULL DEFAULT 'N/A',
  `nhs_number` varchar(30) NOT NULL DEFAULT 'N123',
  `last_notification` date NOT NULL DEFAULT '2000-10-10'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `vnumber`, `fname`, `sname`, `dob`, `diagnosis`, `local_clinic`, `refresh_rate`, `lab_name`, `lab_contact`, `nhs_number`, `last_notification`) VALUES
(36, 'Q853648', 'Mihai', 'Walker', '2000-03-15', 'Diabetes', 'The Lymphoedema', 2, 'Elizabeth Blackwell', 'elizabeth_lab@yahoo.com', '228 352 2641', '2019-03-24'),
(37, 'Q543843', 'Dev', 'Macfarlane', '2006-03-15', 'Flu', 'Nuffield Health', 2, 'Carl Jung ', 'carl_lab@yahoo.com', '673 773 9059', '2000-10-10'),
(38, 'Q853558', 'Alanah', 'Zuniga', '2010-03-18', 'N/A', 'Waterloo Health', 2, 'Louis Pasteur', 'louis_lab@yahoo.com', '131 817 2829', '2000-10-10'),
(39, 'T563996', 'Raheem ', 'Bailey', '2009-03-12', 'Chickenpox ', 'Harley Street Healthcare', 2, 'Jean-Martin Charcot', 'jean_lab@yahoo.com', '383 912 7181', '2000-10-10'),
(40, 'T563996', 'Leonard', 'Moore', '2005-06-15', 'Bronchitis', 'Medical Express ', 2, 'Edward Jenner', 'edward_lab@yahoo.com', '522 869 6008', '2000-10-10'),
(41, 'H874561', 'Judah', 'Montes', '2010-08-11', 'Bronchiolitis', 'Fleet Street Heathcare', 2, 'Avicenna', 'avicenna_lab@yahoo.com', '246 092 8971', '2000-10-10'),
(42, 'K953752', 'Jay', 'Avila', '2004-09-21', 'N/A', 'The Rebalance Clinic', 2, 'Andreas Vesalius', 'andreas_lab@yahoo.com', '141 116 9875', '2000-10-10'),
(43, 'K683942', 'Taha', 'Mendez', '2003-10-11', 'Bronchitis', 'The Smart Clinics', 2, 'Sigmund Freud', 'sigmund_lab@yahoo.com', '175 006 5479', '2000-10-10'),
(44, 'R845773', 'Delores', 'Ramos', '2009-04-23', 'Flu', 'Focus Clinic', 2, 'Sir Joseph Lister', 'joseph_lab@yahoo.com', '37 110 7948', '2000-10-10'),
(45, 'Y943742', 'Lavinia', 'Mcknight', '2010-04-08', 'N/A', 'London Wellbeing ', 2, 'Ignaz Semmelweis', 'ignaz_lab@yahoo.com', '529 012 4578', '2000-10-10'),
(46, 'W936454', 'Wallace', 'Adam', '2012-05-23', 'Flu', 'Brook Euston', 2, 'Sir William Osler', 'william_lab@yahoo.com', '769 938 2315', '2019-03-24'),
(47, 'W227465', 'Oskar', 'Wicks', '2007-01-11', 'N/A', 'The Whiteley', 2, 'Mary Edwards Walker', 'walker_lab@yahoo.com', '649 037 2754', '2000-10-10'),
(48, 'P925375', 'Elis', 'Alford', '2006-10-18', 'Diabetes', 'Margaret Pyke', 2, 'John Simpson Kirkpatrick', 'simpson_lab@yahoo.com', '928 869 1170', '2000-10-10'),
(49, 'A836457', 'Misha', 'Dyer', '2013-10-16', 'N/A', 'Drury Lane ', 2, 'Rex Gregor', 'gregor_lab@yahoo.com', '630 088 5348', '2000-10-10'),
(50, 'G923648', 'Misha', 'Dyer', '2009-01-29', 'Scarlet Fever', 'Wentworth', 2, 'Desmond Doss', 'desmond_lab@yahoo.com', '404 900 8866', '2000-10-10'),
(51, 'T832754', 'Lorcan', 'Oakley', '2000-01-12', 'Fever', 'Medicspot', 2, 'John Bradley', 'bradley_lab@yahoo.com', '533 684 5418', '2000-10-10'),
(52, 'Y385463', 'Willem', 'Morse', '2001-02-20', 'Flu', 'Marie Stopes', 2, 'Genevieve de Galard', 'genevieve_lab@yahoo.com', '880 072 1591', '2000-10-10'),
(53, 'U837543', 'Faisal', 'Dorsey', '2010-06-11', 'Fever', 'BPAS', 2, 'Charles Kelly', 'kelly_lab@yahoo.com', '065 150 3760', '2000-10-10'),
(54, 'I938543', 'Lola', 'Blevins', '2011-05-17', 'Chickenpox', 'CityDoc', 2, 'Thomas W. Bennett', 'test_mail987@yahoo.com', '092 897 2577', '2000-10-10'),
(55, 'O923754', 'Josie', 'Kirkland', '2004-03-19', 'Pinworms', 'Parsons Green', 2, 'Sally Clarke', 'clarke_lab@yahoo.com', '986 300 6343', '2000-10-10');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

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
