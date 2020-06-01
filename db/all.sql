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