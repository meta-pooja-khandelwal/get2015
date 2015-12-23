create database deadlockDemo;
 use deadlockDemo;
 create table ad_data(
   day date not null,
   ad_id int not null,
   client int not null,
   clicks int not null,
   cost int not null,
   primary key(day, ad_id)
) engine=innodb;

insert into ad_data(day, ad_id, client, clicks, cost)
   values
   ('2006-08-01', 1, 1, 10, 100),
   ('2006-08-01', 2, 1, 20, 200),
   ('2006-08-01', 3, 1, 30, 300),
   ('2006-08-01', 4, 1, 40, 400),
   ('2006-08-01', 6, 1, 60, 600);
   
   
   
 

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(10) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ;



CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(10) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
) ;