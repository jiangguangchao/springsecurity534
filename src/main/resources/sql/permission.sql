create table t_permission (
    id int not null auto_increment primary key ,
    url varchar(50) not null unique ,
    descript varchar(100) not null ,
    status smallint not null
);

insert into t_permission (url, descript, status) VALUES (
   '/student/list','学生列表',1
);

insert into t_permission (url, descript, status) VALUES (
    '/student/detail','学生详情',1
);

insert into t_permission (url, descript, status) VALUES (
    '/student/modify','修改学生',1
);

insert into t_permission (url, descript, status) VALUES (
    '/student/add','新增学生',1
);

insert into t_permission (url, descript, status) VALUES (
    '/student/delete','删除学生',1
);

insert into t_permission (url, descript, status) VALUES (
    '/home.html','首页',1
);



