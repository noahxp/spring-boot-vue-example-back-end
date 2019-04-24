
CREATE SCHEMA `logs`; /* DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ; */

USE `logs`;

DROP TABLE IF EXISTS `book_edit_log`;

CREATE TABLE `book_edit_log` (
  `log_id` INT NOT NULL AUTO_INCREMENT,
  `book_name_old` VARCHAR(200) NOT NULL COMMENT '舊書書名',
  `book_name_new` VARCHAR(200) NOT NULL COMMENT '新書名',
  `isdn_old` VARCHAR(20) NOT NULL COMMENT '書本 ISDN',
  `isdn_new` VARCHAR(20) NOT NULL COMMENT '書本 ISDN',
  `editor_emp_no` VARCHAR(10) NULL COMMENT '修改員編',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `update_date` DATE NULL,
  PRIMARY KEY (`log_id`));

