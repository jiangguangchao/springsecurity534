create table t_user (
    id int not null auto_increment primary key ,
    username varchar(50) not null unique ,
    password varchar(50) not null ,
    status smallint not null
);

insert into t_user (username, password, status) VALUES (
    'jgc','jgc',1
);

insert into t_user (username, password, status) VALUES (
    'xm','xm',1
);

alter table t_user modify password varchar(100) not null;

alter table t_user add (createTime datetime not null default now());

alter table t_user add (startWrokTime time);

alter table t_user add (birthday date);

alter table t_user add (leaveTime timestamp);

