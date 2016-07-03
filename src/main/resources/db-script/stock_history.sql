CREATE TABLE `stock_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(20) NOT NULL DEFAULT '',
  `date` date DEFAULT NULL,
  `open` float DEFAULT NULL,
  `high` float DEFAULT NULL,
  `low` float DEFAULT NULL,
  `close` float DEFAULT NULL,
  `volume` bigint(20) DEFAULT NULL,
  `adjClose` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_stock_code_date` (`stock_code`,`date`)
) ENGINE=InnoDB AUTO_INCREMENT=4203 DEFAULT CHARSET=utf8;