CREATE TABLE `order_tbl`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id`        varchar(255) DEFAULT NULL,
    `commodity_code` varchar(255) DEFAULT NULL,
    `count`          int(11) DEFAULT 0,
    `money`          bigint(20) DEFAULT 0,
    `create_time` TIMESTAMP NOT NULL default current_timestamp,
    `update_time` TIMESTAMP NOT NULL on update current_timestamp default current_timestamp,
    PRIMARY KEY (`id`),
    UNIQUE KEY `commodity_code_key` (`commodity_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;