drop   table   id exists   `chapter`;

create  table  `chapter`(
 `id` char(8) not  null   comment 'ID',
 `course_id` char(8) comment '课程',
 `name` varchar(50) comment '名称',
 primary  key  (`id`)
) engine=innodb default  charset=utf8mb4 comment='大章';

insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('1', '1', '语文');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('2', '2', '数学 ');
insert into `course`.`chapter` (`id`, `course_id`, `name`) values ('3', '4', '英语');