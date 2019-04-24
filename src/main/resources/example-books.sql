

CREATE SCHEMA `books`; /* DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ; */

USE `books`;

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `book_id` INT NOT NULL AUTO_INCREMENT,
  `book_name` VARCHAR(200) NOT NULL COMMENT '書名',
  `isdn` VARCHAR(20) NOT NULL COMMENT '書本 ISDN',
  `descript` VARCHAR(2000) NULL COMMENT '書本描述',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `update_date` DATE NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE INDEX `isdn_UNIQUE` (`isdn` ASC));


insert into `book` ( `book_name`, `isdn`) values ('王者歸來 Java Web 整合開發','9865836076');
insert into `book` ( `book_name`, `isdn`) values ('深度學習','7115461473');
insert into `book` ( `book_name`, `isdn`) values ('持續交付2.0 業務引領的 DevOps 精要','7115500010');
insert into `book` ( `book_name`, `isdn`) values ('程序員的數學','7115293686');
insert into `book` ( `book_name`, `isdn`) values ('Android 音視頻開發','7121349965');
insert into `book` ( `book_name`, `isdn`) values ('手把手教你設計 CPU','7115480524');
insert into `book` ( `book_name`, `isdn`) values ('Design Patterns','0201633612');
insert into `book` ( `book_name`, `isdn`) values ('揭開設計模式的秘辛','9864341898');