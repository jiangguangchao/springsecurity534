create table t_role (
    id int not null auto_increment primary key ,
    role varchar(50) not null unique ,
    descript varchar(100) not null ,
    status smallint not null
);

insert into t_role (role, descript, status) VALUES (
    'admin','超级管理员，拥有所有权限',1
);

insert into t_role (role, descript, status) VALUES (
   'user','普通用户，只能查看，没有修改权限',1
);