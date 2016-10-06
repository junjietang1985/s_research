CREATE TABLE `macd` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `emaFast` double DEFAULT NULL,
  `emaSlow` double DEFAULT NULL,
  `diff` double DEFAULT NULL,
  `dem` double DEFAULT NULL,
  `bar` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_stock_code_date` (`stock_code`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
