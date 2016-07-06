CREATE TABLE `stock_ma` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `ma5` float DEFAULT NULL,
  `ma10` float DEFAULT NULL,
  `ma20` float DEFAULT NULL,
  `ma30` float DEFAULT NULL,
  `ma60` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_stock_code_date` (`stock_code`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;