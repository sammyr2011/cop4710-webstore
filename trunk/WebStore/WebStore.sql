-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 11, 2010 at 10:17 PM
-- Server version: 5.1.45
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `webstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `manufacturer`
--

CREATE TABLE IF NOT EXISTS `manufacturer` (
  `mID` int(11) NOT NULL AUTO_INCREMENT,
  `Website` varchar(128) NOT NULL,
  `CompanyName` varchar(128) NOT NULL,
  PRIMARY KEY (`mID`),
  UNIQUE KEY `mID` (`mID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `manufacturer`
--

INSERT INTO `manufacturer` (`mID`, `Website`, `CompanyName`) VALUES
(1, 'http://acme.example.com', 'Acme, inc.'),
(2, 'http://widgetco.example.com', 'Widget Corp'),
(3, 'http://123.example.com', '123 Warehousing'),
(4, 'http://democo.example.com', 'Demo Company'),
(5, 'http://smithco.example.com', 'Smith and Co.');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(64) NOT NULL,
  `ManufacturerID` int(11) NOT NULL,
  `Price` double NOT NULL,
  `Stock` int(11) NOT NULL DEFAULT '0',
  `Image` varchar(32) NOT NULL DEFAULT 'missing.jpg',
  `Description` text NOT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `ManufacturerID` (`ManufacturerID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductID`, `Name`, `ManufacturerID`, `Price`, `Stock`, `Image`, `Description`) VALUES
(1, 'Rock', 2, 2, 997, 'missing.jpg', 'It''s a rock'),
(2, 'Paper', 1, 0.1, 1, 'missing.jpg', 'Acme paper'),
(3, 'Sand (10lbs)', 3, 5, 3, 'missing.jpg', 'Sand, 10 pounds of it'),
(4, 'Slightly used paper', 1, 0.05, 8, 'missing.jpg', 'It''s slightly used paper');

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE IF NOT EXISTS `purchases` (
  `PurchaseID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `Price` double NOT NULL,
  `ShippingAddress` varchar(256) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ShippingPrice` double NOT NULL,
  PRIMARY KEY (`PurchaseID`),
  KEY `UserID` (`UserID`),
  KEY `ProductID` (`ProductID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`PurchaseID`, `UserID`, `ProductID`, `Price`, `ShippingAddress`, `Date`, `ShippingPrice`) VALUES
(9, 2, 1, 2, '1270+Snider+StreetCheraw%2C+CO+81030', '2010-04-11 18:02:14', 5),
(10, 2, 3, 5, '1270+Snider+StreetCheraw%2C+CO+81030', '2010-04-11 18:02:33', 5),
(11, 2, 4, 0.05, '1270+Snider+StreetCheraw%2C+CO+81030', '2010-04-11 18:02:44', 5),
(12, 2, 4, 0.05, '1270+Snider+StreetCheraw%2C+CO+81030', '2010-04-11 18:12:23', 5),
(13, 3, 1, 2, '1518+Whiteman+StreetCamden%2C+NJ+08102+', '2010-04-11 18:13:51', 5),
(14, 3, 3, 5, '1518+Whiteman+StreetCamden%2C+NJ+08102+', '2010-04-11 18:14:05', 5),
(15, 4, 1, 2, '1198+Burning+Memory+LanePhiladelphia%2C+PA+19103', '2010-04-11 18:16:20', 5);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE IF NOT EXISTS `reviews` (
  `ReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Rating` tinyint(4) NOT NULL,
  `Comment` text NOT NULL,
  PRIMARY KEY (`ReviewID`),
  KEY `ProductID` (`ProductID`),
  KEY `UserID` (`UserID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`ReviewID`, `UserID`, `ProductID`, `Rating`, `Comment`) VALUES
(4, 2, 1, 10, 'Top+quality+rock%21'),
(5, 2, 4, 5, 'It%27s+ok%2C+i+guess'),
(6, 3, 1, 2, 'Rock+was+DOA+%3A+-+%28'),
(7, 3, 3, 8, 'Who+can%27t+use+more+sand%3F'),
(8, 4, 1, 5, 'I%27ve+seen+much+higher+quality+rocks+for+sale+on+that+other+site+for+the+same+price');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(32) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Email` varchar(64) NOT NULL,
  `FirstName` varchar(32) NOT NULL,
  `LastName` varchar(32) NOT NULL,
  `Address` varchar(256) NOT NULL,
  `Phone` varchar(16) NOT NULL,
  `IsAdmin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Username`, `Password`, `Email`, `FirstName`, `LastName`, `Address`, `Phone`, `IsAdmin`) VALUES
(1, 'admin', '912ec803b2ce49e4a541068d495ab570', 'GarrettMSteigerwald@example.com', 'Garrett', 'Steigerwald', '4251 Ryder AvenueSeattle, WA 98109 ', '425-276-8066', 1),
(2, 'foo', 'ef238ea00a26528de40ff231e5a97f50', 'LauraEMoore@example.com', 'Laura', 'Moore', '1270 Snider StreetCheraw, CO 81030', '719-853-7651', 0),
(3, 'bar', 'e368d4df326079b608d2849ef3ef9a32', 'MarkRBroadway@example.com', 'Mark', 'Broadway', '1518 Whiteman StreetCamden, NJ 08102 ', '609-935-2590', 0),
(4, 'baz', '88eb8c06eb2e83ff875e0abe15aee92f', 'GloriaTBowman@example.com', 'Gloria', 'Bowman', '1198 Burning Memory LanePhiladelphia, PA 19103', '215-359-3148', 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`ManufacturerID`) REFERENCES `manufacturer` (`mID`);

--
-- Constraints for table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `purchases_ibfk_4` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `purchases_ibfk_3` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE;

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE CASCADE,
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE;
