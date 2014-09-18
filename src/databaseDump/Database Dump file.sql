-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 12, 2014 at 05:31 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `freemrs`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE IF NOT EXISTS `bill` (
  `bill_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `type` varchar(40) NOT NULL,
  `cost` float NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `patient_id` (`patient_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_id`, `patient_id`, `type`, `cost`, `date`) VALUES
(3, 1, 'Drugs', 500, '2014-07-27'),
(4, 1, 'Chanelling', 1500, '2014-07-27'),
(5, 1, 'Drugs', 400, '2014-08-10'),
(6, 1, 'Chanelling', 1000, '2014-08-10');

-- --------------------------------------------------------

--
-- Table structure for table `general_medical_info`
--

CREATE TABLE IF NOT EXISTS `general_medical_info` (
  `patient_id` int(11) NOT NULL,
  `main_medical_problem` varchar(70) NOT NULL,
  `medical_problems` text NOT NULL,
  `allergies` text NOT NULL,
  `immunizations` text NOT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `general_medical_info`
--

INSERT INTO `general_medical_info` (`patient_id`, `main_medical_problem`, `medical_problems`, `allergies`, `immunizations`) VALUES
(1, 'High blood pressure', 'High blood pressure\r\nCholesterol\r\nObese\r\n\r\n', 'Penicillin\nanticonvulsants', 'Tetanus'),
(2, 'diabetic', 'wound in the foot', 'penicillin', 'insiullin');

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE IF NOT EXISTS `insurance` (
  `insuarence_id` int(11) NOT NULL AUTO_INCREMENT,
  `provider` varchar(20) NOT NULL,
  `plan_name` varchar(20) NOT NULL,
  `effective_date` date NOT NULL,
  `policy_number` varchar(20) NOT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`insuarence_id`),
  KEY `patient_id` (`patient_id`),
  KEY `patient_id_2` (`patient_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `insurance`
--

INSERT INTO `insurance` (`insuarence_id`, `provider`, `plan_name`, `effective_date`, `policy_number`, `patient_id`) VALUES
(1, 'Celinco', 'Celinco Life', '2011-01-23', '67654', 1),
(2, 'Janashakthi', 'Life saver', '2009-03-05', '77676', 2);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `birthday` date NOT NULL,
  `sex` varchar(7) NOT NULL,
  `address` varchar(80) NOT NULL,
  `marital_status` varchar(8) NOT NULL,
  `job` varchar(10) NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_id`, `name`, `birthday`, `sex`, `address`, `marital_status`, `job`, `phone`) VALUES
(1, 'nuwan', '1991-03-15', 'male', '8-D,\nBoraliyawatha,\nBemmulla.', 'single', 'student', '0779454744'),
(2, 'kamal', '1996-02-01', 'male', '658,Mirigama', 'single', 'student', '0332267897'),
(7, 'sunil', '1994-02-02', 'male', 'non', 'single', 'none', '1233');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE IF NOT EXISTS `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  `notes` text NOT NULL,
  PRIMARY KEY (`prescription_id`),
  KEY `patient_id` (`patient_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`prescription_id`, `patient_id`, `date_time`, `notes`) VALUES
(1, 1, '2014-07-23 00:00:00', 'Amoxcillin\r\nventolin\r\nParacitamol'),
(2, 1, '2014-07-24 18:24:47', 'lisinopril\natenolol\nDiovan\nBystolic '),
(3, 1, '2014-08-10 20:22:50', 'Panadol');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `start_time` int(11) NOT NULL,
  `end_time` int(11) NOT NULL,
  `location` varchar(40) NOT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `patient_id` (`patient_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`schedule_id`, `patient_id`, `date`, `start_time`, `end_time`, `location`) VALUES
(1, 1, '2014-07-26 00:00:00', 5, 6, 'gampaha'),
(2, 1, '2014-07-25 00:00:00', 5, 6, 'gampaha'),
(3, 1, '2014-07-25 00:00:00', 3, 4, 'colombo'),
(5, 1, '2014-07-28 00:00:00', 5, 6, 'gampaha'),
(6, 1, '2014-08-13 00:00:00', 1, 3, 'colombo'),
(7, 1, '2014-08-13 00:00:00', 3, 4, 'colombo'),
(8, 1, '2014-08-13 00:00:00', 5, 6, 'gampaha'),
(9, 1, '2014-08-15 00:00:00', 1, 3, 'colombo'),
(10, 1, '2014-08-15 00:00:00', 5, 6, 'gampaha'),
(11, 1, '2014-08-14 00:00:00', 3, 4, 'colombo');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE IF NOT EXISTS `userinfo` (
  `username` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `passwordhash` varchar(200) NOT NULL,
  `question` varchar(60) NOT NULL,
  `answer` varchar(200) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`username`, `type`, `passwordhash`, `question`, `answer`) VALUES
('nimesha', 'nurse', '[-44, 87, -62, -9, 74, -56, 30, 116, 48, -125, 108, 4, -122, 109, -29, 107, 9, -76, 48, 43, 107, 47, -57, 4, -6, -5, -54, -36, -29, 73, 116, 105]', 'Who is your grade one teacher?', '[-124, -47, -36, -73, -60, 49, 1, -19, 113, -82, -40, -118, 21, 57, -108, 45, -92, -27, 123, 69, 81, 83, 118, 76, 48, 64, -98, -13, -114, 47, 93, 57]'),
('nuwan', 'doctor', '[-124, -47, -36, -73, -60, 49, 1, -19, 113, -82, -40, -118, 21, 57, -108, 45, -92, -27, 123, 69, 81, 83, 118, 76, 48, 64, -98, -13, -114, 47, 93, 57]', 'Who is your best friend?', '[-124, -47, -36, -73, -60, 49, 1, -19, 113, -82, -40, -118, 21, 57, -108, 45, -92, -27, 123, 69, 81, 83, 118, 76, 48, 64, -98, -13, -114, 47, 93, 57]');

-- --------------------------------------------------------

--
-- Table structure for table `vitals`
--

CREATE TABLE IF NOT EXISTS `vitals` (
  `vital_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` datetime NOT NULL,
  `weight` float NOT NULL,
  `height` float NOT NULL,
  `bp_systolic` int(11) NOT NULL,
  `bp_diastolic` int(11) NOT NULL,
  `pulse` int(11) NOT NULL,
  `temperature` int(11) NOT NULL,
  `temp_location` varchar(10) NOT NULL,
  `oxygen_saturation` int(11) NOT NULL,
  `BMI` float NOT NULL,
  `notes` text NOT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`vital_id`),
  KEY `patient_id` (`patient_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `vitals`
--

INSERT INTO `vitals` (`vital_id`, `date_time`, `weight`, `height`, `bp_systolic`, `bp_diastolic`, `pulse`, `temperature`, `temp_location`, `oxygen_saturation`, `BMI`, `notes`, `patient_id`) VALUES
(1, '2014-07-23 00:00:00', 60, 1.32, 120, 80, 70, 37, 'oral', 98, 21, 'Current patient condition is good and stable.', 1),
(2, '2014-07-20 10:10:20', 60, 1.32, 110, 90, 80, 39, 'oral', 98, 21, '', 1),
(4, '2014-07-24 16:18:37', 60, 1.32, 130, 85, 75, 37, 'oral', 97, 34.4353, 'Current patient condition is good and\nstable.', 1),
(5, '2014-08-10 20:19:55', 61, 1.32, 133, 90, 80, 40, 'oral', 99, 35.0092, 'Current patient condition is good and\nstable.', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `general_medical_info`
--
ALTER TABLE `general_medical_info`
  ADD CONSTRAINT `general_medical_info_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `insurance`
--
ALTER TABLE `insurance`
  ADD CONSTRAINT `insurance_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vitals`
--
ALTER TABLE `vitals`
  ADD CONSTRAINT `vitals_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
