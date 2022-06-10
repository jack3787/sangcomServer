CREATE TABLE `user` (
                        `id`	VARCHAR(20) 	NOT NULL,
                        `password`	VARCHAR(255) 	NOT NULL,
                        `name`	VARCHAR(50) 	NOT NULL,
                        `phone`	VARCHAR(20) 	NOT NULL,
                        `schoolgrade`	INTEGER 	NOT NULL,
                        `schoolclass`	INTEGER 	NOT NULL,
                        `schoolnumber` INTEGER NoT NULL,
                        `role` VARCHAR(20) NOT NULL,
                        `year` INTEGER NOT NULL,
                        `birth`	DATE 	NOT NULL,
                        `email` VARCHAR(100) NOT NULL,
                        PRIMARY KEY(`id`)
);

CREATE TABLE `board` (
                         `board_id`	INTEGER AUTO_INCREMENT 	NOT NULL,
                         `title`	VARCHAR(255) 	NOT NULL,
                         `body`	Text 	NOT NULL,
                         `user_id`	VARCHAR(20) 	NOT NULL,
                         `regdate`	TIMESTAMP 	DEFAULT NOW(),
                         `type` VARCHAR(10) NOT NULL,
                         PRIMARY KEY(`board_id`),
                         FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE
);

CREATE TABLE `reply` (
                         `reply_id`	INTEGER AUTO_INCREMENT 	NOT NULL,
                         `body`	Text 	NOT NULL,
                         `user_id`	VARCHAR(20) 	NOT NULL,
                         `regdate`	TIMESTAMP 	DEFAULT NOW(),
                         `board_id`	INTEGER 	NOT NULL,
                         `parent_id` INTEGER NOT NULL,
                         `level` INTEGER NOT NULL,
                         PRIMARY KEY(`reply_id`),
                         FOREIGN KEY(`board_id`) REFERENCES board(`board_id`) ON DELETE CASCADE,
                         FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE
);


CREATE TABLE `replygood` (
                             `good_id` INTEGER AUTO_INCREMENT NOT NULL,
                             `user_id` VARCHAR(20) NOT NULL,
                             `reply_id` INTEGER NOT NULL,
                             PRIMARY KEY(`good_id`),
                             FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE,
                             FOREIGN KEY(`reply_id`) REFERENCES reply(`reply_id`) ON DELETE CASCADE
);

CREATE TABLE `boardgood` (
                             `good_id` INTEGER AUTO_INCREMENT NOT NULL,
                             `user_id` VARCHAR(20) NOT NULL,
                             `board_id` INTEGER NOT NULL,
                             PRIMARY KEY(`good_id`),
                             FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE,
                             FOREIGN KEY(`board_id`) REFERENCES board(`board_id`) ON DELETE CASCADE
);

CREATE TABLE `scrap` (
                         `scrap_id` INTEGER AUTO_INCREMENT NOT NULL,
                         `user_id` VARCHAR(20) NOT NULL,
                         `board_id` INTEGER NOT NULL,
                         PRIMARY KEY(`scrap_id`),
                         FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE,
                         FOREIGN KEY(`board_id`) REFERENCES board(`board_id`) ON DELETE CASCADE
);


CREATE TABLE `token` (
                         `id` VARCHAR(20) NOT NULL,
                         `token` VARCHAR(512) NOT NULL,
                         PRIMARY KEY(`id`),
                         FOREIGN KEY(`id`) REFERENCES user(`id`) ON DELETE CASCADE
);

CREATE TABLE `imagepath` (
                             `id` VARCHAR(20) NOT NULL,
                             `path` VARCHAR(100) NOT NULL,
                             PRIMARY KEY(`id`),
                             FOREIGN KEY(`id`) REFERENCES user(`id`) ON DELETE CASCADE
);

CREATE TABLE `boardpath` (
                             `path_no` INTEGER AUTO_INCREMENT NOT NULL,
                             `board_id` INTEGER NOT NULL,
                             `path` VARCHAR(100) NOT NULL,
                             PRIMARY KEY(`path_no`),
                             FOREIGN KEY(`board_id`) REFERENCES board(`board_id`) ON DELETE CASCADE
);

CREATE TABLE `boardreport` (
                               `report_id` INTEGER AUTO_INCREMENT NOT NULL,
                               `board_id` INTEGER NOT NULL,
                               `send_id` VARCHAR(20) NOT NULL,
                               `recv_id` VARCHAR(20) NOT NULL,
                               `body` Text NOT NULL,
                               `regdate` TIMESTAMP DEFAULT NOW(),
                               PRIMARY KEY(`report_id`)
);

CREATE TABLE `replyreport` (
                               `report_id` INTEGER AUTO_INCREMENT NOT NULL,
                               `board_id` INTEGER NOT NULL,
                               `reply_id` INTEGER NOT NULL,
                               `send_id` VARCHAR(20) NOT NULL,
                               `recv_id` VARCHAR(20) NOT NULL,
                               `body` Text NOT NULL,
                               `regdate` TIMESTAMP DEFAULT NOW(),
                               PRIMARY KEY(`report_id`),
                               FOREIGN KEY(`board_id`) REFERENCES board(`board_id`) ON DELETE CASCADE
);

CREATE TABLE `authstudent` (
                               `student_id` VARCHAR(20) NOT NULL,
                               `name` VARCHAR(50) NOT NULL,
                               PRIMARY KEY(`student_id`)
);

CREATE TABLE `todolist` (
                            `list_id` INTEGER AUTO_INCREMENT NOT NULL,
                            `user_id` VARCHAR(20) NOT NULL,
                            `body` VARCHAR(100) NOT NULL,
                            `listCheck` Integer NOT NULL DEFAULT 0,
                            `year` INTEGER NOT NULL,
                            `month` INTEGER NOT NULL,
                            `day` INTEGER NOT NULL,
                            PRIMARY KEY(`list_id`),
                            FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE
);

CREATE TABLE `timetable` (
                             `table_id` INTEGER AUTO_INCREMENT NOT NULL,
                             `user_id` VARCHAR(20) NOT NULL,
                             `subject` VARCHAR(20) NOT NULL,
                             `days` CHAR(5) NOT NULL,
                             `period` INTEGER NOT NULL,
                             `location` VARCHAR(10) NOT NULL,
                             `teacher` VARCHAR(10) NOT NULL,
                             PRIMARY KEY(`table_id`),
                             FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE
);

CREATE TABLE `devices` (
                           `user_id` VARCHAR(20) NOT NULL,
                           `device_id` VARCHAR(255) NOT NULL,
                           PRIMARY KEY(`user_id`),
                           FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE
);

CREATE TABLE `notice` (
                          `notice_id` INTEGER AUTO_INCREMENT NOT NULL,
                          `user_id` VARCHAR(20) NOT NULL,
                          `title` VARCHAR(255) NOT NULL,
                          `body` text NOT NULL,
                          `board_id` INTEGER NOT NULL,
                          `regdate` TIMESTAMP DEFAULT NOW(),
                          PRIMARY KEY(`notice_id`),
                          FOREIGN KEY(`user_id`) REFERENCES user(`id`) ON DELETE CASCADE,
                          FOREIGN KEY(`board_id`) REFERENCES board(`board_id`) ON DELETE CASCADE
);