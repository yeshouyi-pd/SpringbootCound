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

-------

CREATE TABLE `course`.`course`(
  `id` CHAR(8) NOT NULL COMMENT 'id',
  `name` VARCHAR(50) NOT NULL COMMENT '名称',
  `summary` VARCHAR(2000) COMMENT '概述',
  `time` INT COMMENT '时长|单位秒',
  `price` DECIMAL(8,2) COMMENT '价格(元)',
  `image` VARCHAR(100) COMMENT '封面',
  `level` CHAR(1) COMMENT '级别| ONE(\"1\",\"初级\"),TWO(\"2\",\"中级\"),THREE(\"3\",\"高级\")',
  `charge` CHAR(1) COMMENT '收费| CHARGE(\"C\"，\"收费\"),FREE(\"F\",\"免费\")',
  `status` CHAR(1) COMMENT '状态|PUBLISH(\"P\", \"发布\"),DRAFT(\"D\",\"草稿\")',
  `enroll` INT DEFAULT 0 COMMENT '报名数',
  `sort` INT COMMENT '顺序',
  `created_at` DATETIME COMMENT '创建时间',
  `updated_at` DATETIME COMMENT '修改时间\',
  PRIMARY KEY (`id`)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE course COMMENT '课程'

INSERT INTO course (id, NAME, summary, TIME, price, image, LEVEL, charge, STATUS, enroll, sort, created_at,updated_at)
VALUES ('00000001','测试课程01', '这是一门测试课程',7200, 19.9, '', 0, 'C', 'D', 100, 0, NOW(), NOW());
