CREATE TABLE `stock` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(20) NOT NULL DEFAULT '',
  `is_sssz300` tinyint(1) NOT NULL DEFAULT '0',
  `allow_sync` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_stock_code` (`stock_code`)
) ENGINE=InnoDB AUTO_INCREMENT=302 DEFAULT CHARSET=utf8;