create table t_user (
    id int not null auto_increment primary key ,
    username varchar(50) not null unique ,
    password varchar(50) not null ,
    status smallint not null
);

insert into t_user (username, password, status) VALUES (
    'jgc','jgc',1
);