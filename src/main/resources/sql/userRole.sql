
create table t_user_role (
       username varchar(20) not null ,
       role_id int not null ,
       status smallint not null,
       primary key (role_id,username)
);

insert into t_user_role (role_id, username, status) VALUES (
    2,'xm',1
);

insert into t_user_role (role_id, username, status) VALUES (
   1,'jgc',1
);



