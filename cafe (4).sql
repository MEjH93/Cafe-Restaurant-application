-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 24, 2020 at 08:56 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafe`
--

-- --------------------------------------------------------

--
-- Table structure for table `background_images`
--

CREATE TABLE `background_images` (
  `id_backgroundImages` int(11) NOT NULL,
  `id_restaurant_offer` int(11) NOT NULL,
  `imageLocation` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `background_images`
--

INSERT INTO `background_images` (`id_backgroundImages`, `id_restaurant_offer`, `imageLocation`) VALUES
(1, 2, 'test');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `id_bill` int(11) NOT NULL,
  `table_number` int(11) DEFAULT NULL,
  `bill_time` timestamp NULL DEFAULT current_timestamp(),
  `id_restaurant` int(11) NOT NULL,
  `waiterName` varchar(100) NOT NULL,
  `finalAmount` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`id_bill`, `table_number`, `bill_time`, `id_restaurant`, `waiterName`, `finalAmount`) VALUES
(50, 4, '2020-07-23 01:28:57', 1, 'Filip', 357),
(51, 5, '2020-07-23 01:29:34', 1, 'm', 1837),
(52, 2, '2020-07-23 01:31:11', 1, 'm', 357),
(53, 2, '2020-07-23 01:31:15', 1, 'm', 11097),
(54, 3, '2020-07-23 17:44:09', 1, 'Filip', 1837),
(55, 3, '2020-07-23 17:44:58', 1, 'Filip', 2194),
(56, 6666, '2020-07-23 17:48:11', 1, 'Filip', 357),
(57, 6666, '2020-07-23 17:48:40', 1, 'Filip', 534),
(58, 2, '2020-07-24 18:12:25', 1, 'm', 1200),
(59, 561, '2020-07-24 18:14:45', 1, 'm', 1400),
(60, 3, '2020-07-24 18:16:19', 1, 'm', 82),
(61, 3, '2020-07-24 18:55:00', 1, 'm', 324);

-- --------------------------------------------------------

--
-- Table structure for table `bill_orders_map`
--

CREATE TABLE `bill_orders_map` (
  `id_bill` int(11) NOT NULL,
  `id_order` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill_orders_map`
--

INSERT INTO `bill_orders_map` (`id_bill`, `id_order`) VALUES
(50, 2),
(51, 2),
(52, 2),
(53, 2),
(54, 2),
(55, 2),
(56, 2),
(57, 2),
(58, 2),
(59, 2),
(60, 2),
(61, 2);

-- --------------------------------------------------------

--
-- Table structure for table `existing_ingredients`
--

CREATE TABLE `existing_ingredients` (
  `id_existing_ingredients` int(11) NOT NULL,
  `id_restaurant` int(55) NOT NULL,
  `id_ingredient` int(55) NOT NULL,
  `quantity` double(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `existing_ingredients`
--

INSERT INTO `existing_ingredients` (`id_existing_ingredients`, `id_restaurant`, `id_ingredient`, `quantity`) VALUES
(1, 1, 3, 0.00),
(2, 1, 1, 0.00),
(5, 1, 12, 0.00),
(6, 1, 6, 0.17),
(7, 1, 15, 39.00),
(51, 1, 8, 0.92),
(54, 1, 2, 0.00),
(58, 1, 7, 0.96),
(59, 1, 11, 0.20),
(62, 1, 4, 37.30),
(63, 1, 14, 9.90),
(65, 1, 9, 29.60),
(66, 1, 5, 8.00),
(68, 1, 21, 3.00),
(74, 1, 22, 11.00),
(75, 1, 23, 1.00),
(76, 1, 24, 10.00),
(77, 1, 25, 111.00),
(79, 1, 10, 12.00),
(80, 1, 13, 3.00),
(81, 1, 18, 8.00),
(83, 1, 12, 0.00),
(84, 1, 25, 111.00),
(85, 1, 23, 1.00),
(86, 1, 19, 3.00),
(87, 1, 20, 16.00),
(89, 1, 12, 0.00),
(91, 1, 26, 4.00),
(92, 1, 27, 12.00),
(95, 1, 18, 31.00),
(117, 1, 28, 12.00),
(120, 1, 10, 12.00),
(122, 1, 20, 16.00),
(123, 1, 17, 18.00),
(130, 1, 38, 66.98),
(131, 1, 38, 66.98),
(132, 1, 39, 33.33),
(135, 1, 42, 22.22),
(136, 1, 43, 55.76),
(138, 1, 45, 0.00),
(141, 1, 48, 11.00),
(142, 1, 49, 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `id_ingredient` int(11) NOT NULL,
  `price` float NOT NULL,
  `ingredient_name` varchar(50) NOT NULL,
  `measurement_quantity` enum('l','kg','pieces') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`id_ingredient`, `price`, `ingredient_name`, `measurement_quantity`) VALUES
(1, 1237, 'Vodka', 'l'),
(2, 162, 'Krupnik', 'l'),
(3, 1337, 'Malibu', 'l'),
(4, 247, 'Jagermeister', 'l'),
(5, 1187, 'Grey Goose', 'l'),
(6, 1587, 'Vermouth', 'l'),
(7, 357, 'Sugar', 'kg'),
(8, 1367, 'Rum', 'l'),
(9, 82, 'Mineral Water', 'l'),
(10, 357, 'Lime', 'kg'),
(11, 1837, 'Tequila', 'l'),
(12, 287, 'Peach Juice', 'l'),
(13, 367, 'Pineapple Juice', 'kg'),
(14, 177, 'Grog', 'kg'),
(15, 987, 'Chicago Pizza', 'pieces'),
(17, 97, 'Fanta', 'pieces'),
(18, 62, 'Coffee', 'pieces'),
(19, 1036, 'St. Louis Pizza', 'pieces'),
(20, 437, 'Sandwich', 'pieces'),
(21, 2148, 'Detroit Pizza', 'pieces'),
(22, 162, 'Salt', 'kg'),
(23, 977, 'Espresso Coffee', 'kg'),
(24, 55.55, 'Milk', 'l'),
(25, 187, 'Random', 'l'),
(26, 200, 'test', 'kg'),
(27, 122, 'Sprite', 'pieces'),
(28, 127, 'Seven Up', 'pieces'),
(29, 55.55, 'mejh', 'kg'),
(30, 10, 'arsa', 'pieces'),
(31, 55, 'ime', 'kg'),
(32, 5, 'm', 'kg'),
(33, 1, 'a', 'kg'),
(34, 5, 'b', 'kg'),
(35, 55.55, 'arsenije', 'kg'),
(36, 80, 'radivoje', 'kg'),
(37, 0.5, 'radivojcevic', 'kg'),
(38, 77.77, 'ukurac', 'pieces'),
(39, 33.33, 'leksa', 'kg'),
(42, 22.22, 'aghf', 'kg'),
(43, 55.55, 'arsolije', 'pieces'),
(45, 0, 'IPHONE', 'pieces'),
(48, 1, 'IPHONES', 'pieces'),
(49, 1, 'IPHONEZ', 'pieces');

-- --------------------------------------------------------

--
-- Table structure for table `order_offers`
--

CREATE TABLE `order_offers` (
  `id_order` int(11) NOT NULL,
  `table_number` int(11) NOT NULL,
  `price` double NOT NULL,
  `id_ingredient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_offers`
--

INSERT INTO `order_offers` (`id_order`, `table_number`, `price`, `id_ingredient`) VALUES
(2, 5, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `restaurants`
--

CREATE TABLE `restaurants` (
  `id_restaurant` int(11) NOT NULL,
  `name_restaurant` varchar(40) DEFAULT NULL,
  `restaurantImage` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `restaurants`
--

INSERT INTO `restaurants` (`id_restaurant`, `name_restaurant`, `restaurantImage`) VALUES
(1, 'First Restaurant', 'Server/src/com/comtrade/images/restaurant_1_image.png');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_offer`
--

CREATE TABLE `restaurant_offer` (
  `id_restaurant_offer` int(11) NOT NULL,
  `restaurant_offer_name` varchar(50) NOT NULL,
  `restaurant_offer_price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `restaurant_offer`
--

INSERT INTO `restaurant_offer` (`id_restaurant_offer`, `restaurant_offer_name`, `restaurant_offer_price`) VALUES
(1, 'Vodka', 200),
(2, 'JuiceCranberry', 150),
(3, 'PizzaSicilian', 1200),
(4, 'JuiceLime', 150),
(5, 'Coffee', 140),
(6, 'Vermouth', 200),
(7, 'Fanta', 180),
(8, 'CocaCola', 180);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_offer_ingredients`
--

CREATE TABLE `restaurant_offer_ingredients` (
  `id_restaurant_offer_ingredients` int(11) NOT NULL,
  `id_restaurant_offer` int(11) NOT NULL,
  `id_ingredient` int(11) NOT NULL,
  `quantity` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `restaurant_offer_ingredients`
--

INSERT INTO `restaurant_offer_ingredients` (`id_restaurant_offer_ingredients`, `id_restaurant_offer`, `id_ingredient`, `quantity`) VALUES
(7, 3, 5, 0.02),
(9, 5, 12, 0.1),
(11, 7, 1, 0.05),
(12, 2, 2, 0.2),
(13, 4, 4, 0.2),
(14, 6, 6, 0.05),
(15, 8, 9, 0.2),
(16, 2, 11, 0.05),
(17, 1, 12, 0.2);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_table`
--

CREATE TABLE `restaurant_table` (
  `id_restaurant_table` int(11) NOT NULL,
  `table_number` int(11) NOT NULL,
  `id_restaurant` int(11) NOT NULL,
  `table_status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `restaurant_table`
--

INSERT INTO `restaurant_table` (`id_restaurant_table`, `table_number`, `id_restaurant`, `table_status`) VALUES
(1, 1, 1, 0),
(2, 2, 1, 0),
(3, 3, 1, 0),
(4, 4, 1, 0),
(5, 5, 1, 0),
(6, 6, 1, 0),
(7, 7, 1, 0),
(8, 8, 1, 0),
(9, 9, 1, 0),
(10, 11, 1, 0),
(11, 21, 1, 0),
(12, 31, 1, 0),
(13, 41, 1, 0),
(16, 55, 1, 0),
(17, 101, 1, 0),
(18, 102, 1, 0),
(19, 545, 1, 0),
(20, 533, 1, 0),
(21, 523, 1, 0),
(22, 569, 1, 0),
(23, 6666, 1, 0),
(24, 639, 1, 0),
(25, 973, 1, 0),
(26, 561, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `role_name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `role_name`) VALUES
(1, 'owner'),
(2, 'manager'),
(3, 'waiter');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `status` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `first_name`, `username`, `password`, `status`) VALUES
(1, 'Mihajlo', 'mejh', 'mejh', 1),
(2, 'Filip', 'arsa', 'arsa', 1),
(3, 'Dejan', 'deki', 'deki', 1),
(4, 'a', 'a', '1', NULL),
(5, 'b', 'b', '1', 0),
(6, 'miha', 'miha123', '12345', 0),
(7, 'a', 'a', 'a', 0),
(8, 'a', 'a', 'a', 0),
(9, 'a', 'a', 'a', 0),
(10, 'Filip Arsenijevic', 'Arso', 'arsa123', 0),
(11, 'm', 'm', 'm', 0),
(12, 'testadmin', 'adminko', '123', 0),
(13, 'mimimim', 'mama', '12345', 1),
(14, 'Radivoje', 'Kurwa', '12345', 1),
(15, 'rada', 'rada', '12345', 1),
(16, 'milica', 'milica', '555333', 1),
(17, 'm', 'm', '1', 1),
(18, 'i', 'i', '1', 1),
(19, 'f', 'f', '1', 0),
(20, 'a', 'a', '1', 0),
(21, 'a', 'a', '1', 1),
(22, 'f', 'f', '1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_restaurant_map`
--

CREATE TABLE `user_restaurant_map` (
  `id_user` int(11) NOT NULL,
  `id_restaurant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_restaurant_map`
--

INSERT INTO `user_restaurant_map` (`id_user`, `id_restaurant`) VALUES
(1, 1),
(1, 1),
(2, 1),
(2, 1),
(2, 1),
(3, 1),
(3, 1),
(3, 1),
(3, 1),
(3, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1),
(20, 1),
(21, 1),
(22, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role_map`
--

CREATE TABLE `user_role_map` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role_map`
--

INSERT INTO `user_role_map` (`id_user`, `id_role`) VALUES
(1, 1),
(3, 2),
(2, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 3),
(20, 3),
(21, 1),
(22, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `background_images`
--
ALTER TABLE `background_images`
  ADD PRIMARY KEY (`id_backgroundImages`),
  ADD UNIQUE KEY `id_backgroundImages` (`id_backgroundImages`),
  ADD KEY `id_restaurant_offer` (`id_restaurant_offer`),
  ADD KEY `id_restaurant_offer_2` (`id_restaurant_offer`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id_bill`),
  ADD KEY `bill_ibfk_1` (`table_number`),
  ADD KEY `bill_ibfk_3` (`id_restaurant`);

--
-- Indexes for table `bill_orders_map`
--
ALTER TABLE `bill_orders_map`
  ADD KEY `id_bill` (`id_bill`);

--
-- Indexes for table `existing_ingredients`
--
ALTER TABLE `existing_ingredients`
  ADD PRIMARY KEY (`id_existing_ingredients`),
  ADD KEY `mejh` (`id_ingredient`),
  ADD KEY `mejh2` (`id_restaurant`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id_ingredient`),
  ADD UNIQUE KEY `ingredient_name` (`ingredient_name`),
  ADD UNIQUE KEY `ingredient_name_2` (`ingredient_name`),
  ADD UNIQUE KEY `ingredient_name_3` (`ingredient_name`),
  ADD UNIQUE KEY `ingredient_name_4` (`ingredient_name`);

--
-- Indexes for table `order_offers`
--
ALTER TABLE `order_offers`
  ADD PRIMARY KEY (`id_order`);

--
-- Indexes for table `restaurants`
--
ALTER TABLE `restaurants`
  ADD PRIMARY KEY (`id_restaurant`),
  ADD UNIQUE KEY `name_restaurant` (`name_restaurant`);

--
-- Indexes for table `restaurant_offer`
--
ALTER TABLE `restaurant_offer`
  ADD PRIMARY KEY (`id_restaurant_offer`);

--
-- Indexes for table `restaurant_offer_ingredients`
--
ALTER TABLE `restaurant_offer_ingredients`
  ADD PRIMARY KEY (`id_restaurant_offer_ingredients`),
  ADD KEY `id_restaurant_offer` (`id_restaurant_offer`),
  ADD KEY `id_ingredient` (`id_ingredient`);

--
-- Indexes for table `restaurant_table`
--
ALTER TABLE `restaurant_table`
  ADD PRIMARY KEY (`id_restaurant_table`),
  ADD KEY `id_restaurant` (`id_restaurant`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `user_restaurant_map`
--
ALTER TABLE `user_restaurant_map`
  ADD KEY `id_restaurant` (`id_restaurant`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `user_role_map`
--
ALTER TABLE `user_role_map`
  ADD KEY `id_role` (`id_role`),
  ADD KEY `id_user` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id_bill` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `existing_ingredients`
--
ALTER TABLE `existing_ingredients`
  MODIFY `id_existing_ingredients` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id_ingredient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `order_offers`
--
ALTER TABLE `order_offers`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=712;

--
-- AUTO_INCREMENT for table `restaurants`
--
ALTER TABLE `restaurants`
  MODIFY `id_restaurant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `restaurant_offer`
--
ALTER TABLE `restaurant_offer`
  MODIFY `id_restaurant_offer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121011;

--
-- AUTO_INCREMENT for table `restaurant_table`
--
ALTER TABLE `restaurant_table`
  MODIFY `id_restaurant_table` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `background_images`
--
ALTER TABLE `background_images`
  ADD CONSTRAINT `images_ibfk_1` FOREIGN KEY (`id_restaurant_offer`) REFERENCES `restaurant_offer` (`id_restaurant_offer`);

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_3` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

--
-- Constraints for table `bill_orders_map`
--
ALTER TABLE `bill_orders_map`
  ADD CONSTRAINT `bill_orders_map_ibfk_1` FOREIGN KEY (`id_bill`) REFERENCES `bill` (`id_bill`);

--
-- Constraints for table `existing_ingredients`
--
ALTER TABLE `existing_ingredients`
  ADD CONSTRAINT `mejh` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredients` (`id_ingredient`),
  ADD CONSTRAINT `mejh2` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

--
-- Constraints for table `restaurant_offer_ingredients`
--
ALTER TABLE `restaurant_offer_ingredients`
  ADD CONSTRAINT `restaurant_offer_ingredients_ibfk_1` FOREIGN KEY (`id_restaurant_offer`) REFERENCES `restaurant_offer` (`id_restaurant_offer`),
  ADD CONSTRAINT `restaurant_offer_ingredients_ibfk_2` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredients` (`id_ingredient`);

--
-- Constraints for table `restaurant_table`
--
ALTER TABLE `restaurant_table`
  ADD CONSTRAINT `restaurant_table_ibfk_1` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

--
-- Constraints for table `user_restaurant_map`
--
ALTER TABLE `user_restaurant_map`
  ADD CONSTRAINT `user_restaurant_map_ibfk_1` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`),
  ADD CONSTRAINT `user_restaurant_map_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `user_role_map`
--
ALTER TABLE `user_role_map`
  ADD CONSTRAINT `user_role_map_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `user_role_map_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
