drop   table   id exists   `chapter`;

create  table  `chapter`(
 `id` char(8) not  null   comment 'ID',
 `course_id` char(8) comment '课程',
 `name` varchar(50) comment '名称',
 primary  key  (`id`)
) engine=innodb default  charset=utf8mb4 comment='大章';

insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('5', '5', '语文');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('6', '6', '数学 ');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('7', '7', '英语');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('8', '8', '语文');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('9', '9', '数学 ');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('10', '10', '英语');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('11', '11', '语文');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('12', '12', '数学 ');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('13', '13', '英语');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('14', '14', '语文');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('15', '15', '数学 ');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('16', '16', '英语');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('17', '17', '语文');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('18', '18', '数学 ');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('19', '19', '英语');
---------------
CREATE TABLE `course`.`section`(
                                   `id` CHAR(8) NOT NULL COMMENT 'ID',
                                   `title` VARCHAR(50) COMMENT '标题',
                                   `course_id` CHAR(8) COMMENT '课程|course.id',
                                   `chapter_id` CHAR(8) COMMENT '大章|chapter.id',
                                   `video` VARCHAR(200) COMMENT '视频',
                                   `time` INT COMMENT '时长|单位秒',
                                   `charge` CHAR(1) COMMENT '收费|C 收费; F免费',
                                   `sort` INT COMMENT '顺序',
                                   `created_at` DATETIME COMMENT '创建时间',
                                   `updated_at` DATETIME COMMENT '修改时间',
                                   PRIMARY KEY (`id`)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO section (`id`, `title`, `course_id`, `chapter_id`, `video`, `time`, `charge`, `sort`, `created_at`, `updated_at` )
VALUES ( '00000001','测试小节01','00000001','000000', '',500, 'F', 1, now(), now());