-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 09, 2020 at 04:31 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_commerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `book_p_id` int(11) NOT NULL,
  `book_c_id` int(11) NOT NULL DEFAULT 1,
  `book_author` varchar(100) NOT NULL,
  `book_publ_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`book_id`, `book_p_id`, `book_c_id`, `book_author`, `book_publ_date`) VALUES
(1, 1, 1, 'Franz Kafka', '2007-01-01'),
(2, 2, 1, 'Franz Kafka', '2017-11-01'),
(3, 3, 1, 'Jaya Dev', '2018-11-01'),
(4, 4, 1, 'Jayaprakash Narayan', '1997-11-01'),
(5, 5, 1, 'G. B. Shaw', '2000-11-01');

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `b_id` int(11) NOT NULL,
  `b_name` varchar(100) NOT NULL,
  `b_des` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`b_id`, `b_name`, `b_des`) VALUES
(1, 'Bookends', 'Book Company'),
(2, 'Brisk Books', 'Book Company'),
(3, 'Book Barn', 'Book Company'),
(4, 'Apple', 'Electronic Company'),
(5, 'Samsung', 'Electronic Company'),
(6, 'Lenovo', 'Electronic Company'),
(7, 'Dell', 'Electronic Company'),
(8, 'Hooker Furniture', 'Furniture Company'),
(9, 'Basset', 'Furniture Company'),
(10, 'Stanley', 'Furniture Company'),
(11, 'Broyhill', 'Furniture Company'),
(12, 'Xioami', 'Electronic Company');

-- --------------------------------------------------------

--
-- Table structure for table `carts`
--

CREATE TABLE `carts` (
  `cart_id` int(11) NOT NULL,
  `cart_cust_id` int(11) NOT NULL,
  `cart_add_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `carts`
--

INSERT INTO `carts` (`cart_id`, `cart_cust_id`, `cart_add_date`) VALUES
(1, 2, '2020-01-10'),
(2, 4, '2020-02-01'),
(3, 5, '2020-01-30');

-- --------------------------------------------------------

--
-- Table structure for table `cart_details`
--

CREATE TABLE `cart_details` (
  `cart_id` int(11) NOT NULL,
  `cart_prd_id` int(11) NOT NULL,
  `cart_prd_qunt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart_details`
--

INSERT INTO `cart_details` (`cart_id`, `cart_prd_id`, `cart_prd_qunt`) VALUES
(1, 19, 1),
(1, 11, 1),
(2, 18, 2),
(2, 10, 1),
(3, 16, 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(100) NOT NULL,
  `cat_description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`cat_id`, `cat_name`, `cat_description`) VALUES
(1, 'Book', 'xxx'),
(2, 'Electronics', 'xxx'),
(3, 'Furniture', 'xxx');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cust_id` int(11) NOT NULL,
  `cust_name` varchar(100) NOT NULL,
  `cust_email` varchar(100) NOT NULL,
  `cust_contact` varchar(100) NOT NULL
) ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cust_id`, `cust_name`, `cust_email`, `cust_contact`) VALUES
(1, 'Rakesh', 'xyz@gmail.com', '1234'),
(2, 'Abhinav', 'abc@gmail.com', '1234'),
(3, 'Sohan', 'jkl@yahoo.com', '5678'),
(4, 'Ajay', 'ajy@yahoo.com', '3478'),
(5, 'Lavish', 'lavi@outlook.com', '2678');

-- --------------------------------------------------------

--
-- Table structure for table `cust_address`
--

CREATE TABLE `cust_address` (
  `cust_id` int(11) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cust_address`
--

INSERT INTO `cust_address` (`cust_id`, `city`, `state`) VALUES
(1, 'Mumbai', 'Maharashtra'),
(1, 'Pune', 'Maharashtra'),
(2, 'Delhi', 'Delhi'),
(3, 'Ajmer', 'Rajasthan'),
(4, 'Jaipur', 'Rajasthan'),
(5, 'Kota', 'Rajasthan');

-- --------------------------------------------------------

--
-- Table structure for table `diwali_deals`
--

CREATE TABLE `diwali_deals` (
  `p_id` int(11) NOT NULL,
  `p_name` varchar(100) NOT NULL,
  `p_price` int(11) NOT NULL,
  `p_type` varchar(100) NOT NULL,
  `p_vndr_id` int(11) NOT NULL,
  `p_desc` varchar(100) NOT NULL,
  `p_quantity` int(11) NOT NULL,
  `p_add_dt` date NOT NULL,
  `p_vw_count` int(11) NOT NULL,
  `p_vw_date` date NOT NULL,
  `p_cat_id` int(11) NOT NULL,
  `p_b_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `diwali_deals`
--

INSERT INTO `diwali_deals` (`p_id`, `p_name`, `p_price`, `p_type`, `p_vndr_id`, `p_desc`, `p_quantity`, `p_add_dt`, `p_vw_count`, `p_vw_date`, `p_cat_id`, `p_b_id`) VALUES
(1, 'Unchained Voice', 1425, 'book', 6, 'xxx', 1, '2020-02-01', 25, '2019-10-01', 1, 3),
(2, 'Wash Stands', 6649, 'furniture', 4, 'xxx', 1, '2020-01-01', 23, '2019-10-05', 3, 8),
(3, 'Apple Tablet 7gen', 76950, 'Tablet', 1, 'xxx', 5, '2019-11-11', 9, '2019-12-01', 2, 4),
(4, 'Apple Tablet 6gen', 28500, 'Tablet', 2, 'xxx', 6, '2019-12-01', 110, '2020-01-01', 2, 4),
(5, 'Apple Laptop', 142500, 'Laptop', 2, 'xxx', 3, '2019-12-05', 150, '2020-02-02', 2, 4),
(6, 'Xioami Redmi', 14250, 'Mobile', 5, 'xxx', 4, '2020-01-01', 200, '2019-11-30', 2, 12),
(7, 'Xioami Speaker', 14108, 'Speaker', 6, 'xxx', 3, '2020-02-05', 6, '2020-01-01', 2, 12),
(8, 'Lenvo Laptop', 47500, 'Laptop', 5, 'xxx', 5, '2020-02-01', 80, '2020-01-30', 2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `ord_id` int(11) NOT NULL,
  `ord_cust_id` int(11) NOT NULL,
  `ord_add_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`ord_id`, `ord_cust_id`, `ord_add_date`) VALUES
(1, 2, '2020-01-10'),
(2, 2, '2020-02-10'),
(3, 1, '2020-01-10'),
(4, 1, '2020-02-10'),
(5, 1, '2020-01-19'),
(6, 1, '2019-12-31'),
(7, 5, '2020-01-30');

-- --------------------------------------------------------

--
-- Table structure for table `ord_details`
--

CREATE TABLE `ord_details` (
  `ord_id` int(11) NOT NULL,
  `ord_prd_id` int(11) NOT NULL,
  `ord_prd_qunt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ord_details`
--

INSERT INTO `ord_details` (`ord_id`, `ord_prd_id`, `ord_prd_qunt`) VALUES
(1, 19, 1),
(1, 11, 1),
(2, 18, 2),
(2, 10, 1),
(3, 16, 1),
(4, 5, 1),
(5, 6, 2),
(6, 1, 1),
(7, 8, 2),
(7, 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `prod_details`
--

CREATE TABLE `prod_details` (
  `p_id` int(11) NOT NULL,
  `p_name` varchar(100) NOT NULL,
  `p_price` int(11) NOT NULL,
  `p_type` varchar(100) NOT NULL,
  `p_vndr_id` int(11) NOT NULL,
  `p_desc` varchar(100) NOT NULL,
  `p_quantity` int(11) NOT NULL,
  `p_add_dt` date NOT NULL,
  `p_vw_count` int(11) NOT NULL,
  `p_vw_date` date NOT NULL,
  `p_cat_id` int(11) NOT NULL,
  `p_b_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prod_details`
--

INSERT INTO `prod_details` (`p_id`, `p_name`, `p_price`, `p_type`, `p_vndr_id`, `p_desc`, `p_quantity`, `p_add_dt`, `p_vw_count`, `p_vw_date`, `p_cat_id`, `p_b_id`) VALUES
(1, 'Opening Space', 400, 'book', 7, 'xxx', 3, '2015-10-01', 5, '2019-11-01', 1, 1),
(2, 'African Folktales', 900, 'book', 7, 'xxx', 3, '2015-10-01', 15, '2019-11-01', 1, 3),
(3, 'Unchained Voice', 1500, 'book', 6, 'xxx', 1, '2020-02-01', 25, '2019-10-01', 1, 3),
(4, 'Love Child', 2000, 'book', 8, 'xxx', 3, '2019-10-01', 2, '2019-09-01', 1, 2),
(5, 'Oral Epics from Africa', 600, 'book', 6, 'xxx', 8, '1995-10-01', 1, '2019-08-01', 1, 2),
(6, 'Wodden Side Table', 5000, 'Table', 9, 'xxx', 2, '2015-09-01', 4, '2019-11-11', 3, 8),
(7, 'Shelves Shoe Cabinet', 13500, 'Wooden Shelve', 9, 'xxx', 3, '2016-01-01', 6, '2019-12-12', 3, 9),
(8, 'Zigzag Corner Wall', 6000, 'Wooden Corner Wall', 3, 'xxx', 1, '2017-12-12', 15, '2019-09-16', 3, 10),
(9, 'Foldeable Stool', 1000, 'Stool', 9, 'xxx', 2, '2018-01-19', 21, '2019-11-11', 3, 10),
(10, 'Bedside Tables', 2000, 'Table', 3, 'xxx', 2, '2018-09-15', 11, '2019-11-06', 3, 11),
(11, 'Ward Robes', 11475, 'furniture', 4, 'xxx', 6, '2019-09-15', 9, '2019-12-06', 3, 11),
(12, 'Wash Stands', 6999, 'furniture', 4, 'xxx', 1, '2020-01-01', 23, '2019-10-05', 3, 8),
(13, 'Apple iphone-x', 135000, 'Mobile', 1, 'xxx', 10, '2019-10-01', 5, '2019-11-11', 2, 4),
(14, 'Apple Tablet 7gen', 81000, 'Tablet', 1, 'xxx', 5, '2019-11-11', 9, '2019-12-01', 2, 4),
(15, 'Apple Tablet 6gen', 30000, 'Tablet', 2, 'xxx', 6, '2019-12-01', 110, '2020-01-01', 2, 4),
(16, 'Apple Laptop', 150000, 'Laptop', 2, 'xxx', 3, '2019-12-05', 150, '2020-02-02', 2, 4),
(17, 'Xioami Redmi', 15000, 'Mobile', 5, 'xxx', 4, '2020-01-01', 200, '2019-11-30', 2, 12),
(18, 'Xioami Speaker', 14850, 'Speaker', 6, 'xxx', 3, '2020-02-05', 6, '2020-01-01', 2, 12),
(19, 'Lenvo Laptop', 50000, 'Laptop', 5, 'xxx', 5, '2020-02-01', 80, '2020-01-30', 2, 6),
(20, 'Dell Laptop', 101250, 'Laptop', 1, 'xxx', 1, '2019-10-01', 9, '2020-02-05', 2, 7);

-- --------------------------------------------------------

--
-- Table structure for table `retailer`
--

CREATE TABLE `retailer` (
  `ret_id` int(11) NOT NULL,
  `ret_name` varchar(100) NOT NULL,
  `ret_address` varchar(100) NOT NULL,
  `ret_city` varchar(100) NOT NULL,
  `ret_state` varchar(100) NOT NULL,
  `ret_email` varchar(100) NOT NULL,
  `ret_contact` varchar(100) NOT NULL
) ;

--
-- Dumping data for table `retailer`
--

INSERT INTO `retailer` (`ret_id`, `ret_name`, `ret_address`, `ret_city`, `ret_state`, `ret_email`, `ret_contact`) VALUES
(1, 'BigWorld', 'Bangalore', 'Bangalore', 'Karnatka', 'bw @gmail.com', '1234'),
(2, 'DASM', 'Bangalore', 'Bangalore', 'Karnatka', 'da@gmail.com', '6766'),
(3, 'GKP', 'Hyderabad', 'Hyderabad', 'Telangana', 'gkp@gmail.com', '9999'),
(4, 'Lepakshi', 'Mumbai', 'Mumbai', 'Maharashtra', 'le@gmail.com', '0871'),
(5, 'Peepul', 'Mumbai', 'Mumbai', 'Maharashtra', 'pee@gmail.com', '5723'),
(6, 'luxe', 'Pune', 'Pune', 'Maharashtra', 'lu@gmail.com', '6021'),
(7, 'Viya', 'Delhi', 'Delhi', 'Delhi', 'vy@gmail.com', '3581'),
(8, 'craft', 'Ahemdabad', 'Ahemdabad', 'Gujrat', 'cr@gmail.com', '1234'),
(9, 'IKea', 'Hyderabad', 'Hyderabad', 'Telangana', 'ikea@outlook.com', '3344');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `cid` int(11) DEFAULT -1,
  `cname` varchar(60) NOT NULL DEFAULT 'Anonymous',
  `rating` int(11) NOT NULL,
  `review` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`rid`, `pid`, `cid`, `cname`, `rating`, `review`) VALUES
(1, 13, NULL, 'Anonymous', 1, 'Bad'),
(2, 13, 5, 'Lavish', 3, 'Average'),
(3, 12, 1, 'Rakesh', 4, 'Good'),
(4, 10, 3, 'Sohan', 5, 'Excellent'),
(5, 16, 5, 'Lavish', 5, 'Excellent'),
(6, 8, NULL, 'Anonymous', 1, 'Good'),
(11, 5, 1, 'Rakesh', 3, 'Average'),
(12, 6, 1, 'Rakesh', 4, 'Good'),
(13, 1, 1, 'Rakesh', 5, 'Excellent');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`),
  ADD KEY `book_p_id` (`book_p_id`),
  ADD KEY `book_c_id` (`book_c_id`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `cart_cust_id` (`cart_cust_id`);

--
-- Indexes for table `cart_details`
--
ALTER TABLE `cart_details`
  ADD KEY `cart_id` (`cart_id`),
  ADD KEY `cart_prd_id` (`cart_prd_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cust_id`);

--
-- Indexes for table `cust_address`
--
ALTER TABLE `cust_address`
  ADD KEY `cust_id` (`cust_id`);

--
-- Indexes for table `diwali_deals`
--
ALTER TABLE `diwali_deals`
  ADD PRIMARY KEY (`p_id`),
  ADD KEY `p_cat_id` (`p_cat_id`),
  ADD KEY `p_b_id` (`p_b_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`ord_id`),
  ADD KEY `ord_cust_id` (`ord_cust_id`);

--
-- Indexes for table `ord_details`
--
ALTER TABLE `ord_details`
  ADD KEY `ord_id` (`ord_id`),
  ADD KEY `ord_prd_id` (`ord_prd_id`);

--
-- Indexes for table `prod_details`
--
ALTER TABLE `prod_details`
  ADD PRIMARY KEY (`p_id`),
  ADD KEY `p_cat_id` (`p_cat_id`),
  ADD KEY `p_b_id` (`p_b_id`);

--
-- Indexes for table `retailer`
--
ALTER TABLE `retailer`
  ADD PRIMARY KEY (`ret_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`rid`),
  ADD KEY `pid` (`pid`),
  ADD KEY `cid` (`cid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `carts`
--
ALTER TABLE `carts`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `cust_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `diwali_deals`
--
ALTER TABLE `diwali_deals`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `ord_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `prod_details`
--
ALTER TABLE `prod_details`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `retailer`
--
ALTER TABLE `retailer`
  MODIFY `ret_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `rid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`book_p_id`) REFERENCES `prod_details` (`p_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `book_ibfk_2` FOREIGN KEY (`book_c_id`) REFERENCES `category` (`cat_id`) ON DELETE CASCADE;

--
-- Constraints for table `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`cart_cust_id`) REFERENCES `customer` (`cust_id`) ON DELETE CASCADE;

--
-- Constraints for table `cart_details`
--
ALTER TABLE `cart_details`
  ADD CONSTRAINT `cart_details_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `cart_details_ibfk_2` FOREIGN KEY (`cart_prd_id`) REFERENCES `prod_details` (`p_id`) ON DELETE CASCADE;

--
-- Constraints for table `cust_address`
--
ALTER TABLE `cust_address`
  ADD CONSTRAINT `cust_address_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`cust_id`) ON DELETE CASCADE;

--
-- Constraints for table `diwali_deals`
--
ALTER TABLE `diwali_deals`
  ADD CONSTRAINT `diwali_deals_ibfk_1` FOREIGN KEY (`p_cat_id`) REFERENCES `category` (`cat_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `diwali_deals_ibfk_2` FOREIGN KEY (`p_b_id`) REFERENCES `brand` (`b_id`) ON DELETE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`ord_cust_id`) REFERENCES `customer` (`cust_id`) ON DELETE CASCADE;

--
-- Constraints for table `ord_details`
--
ALTER TABLE `ord_details`
  ADD CONSTRAINT `ord_details_ibfk_1` FOREIGN KEY (`ord_id`) REFERENCES `orders` (`ord_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `ord_details_ibfk_2` FOREIGN KEY (`ord_prd_id`) REFERENCES `prod_details` (`p_id`) ON DELETE CASCADE;

--
-- Constraints for table `prod_details`
--
ALTER TABLE `prod_details`
  ADD CONSTRAINT `prod_details_ibfk_1` FOREIGN KEY (`p_cat_id`) REFERENCES `category` (`cat_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `prod_details_ibfk_2` FOREIGN KEY (`p_b_id`) REFERENCES `brand` (`b_id`) ON DELETE CASCADE;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `prod_details` (`p_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `customer` (`cust_id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
