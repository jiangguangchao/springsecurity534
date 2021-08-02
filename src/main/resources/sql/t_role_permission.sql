

create table t_role_permission (
  role_id int not null ,
  perm_id int not null ,
  status smallint not null,
  primary key (role_id,perm_id)

);

insert into t_role_permission (role_id, perm_id, status) VALUES (
    2,1,1
);

insert into t_role_permission (role_id, perm_id, status) VALUES (
    2,2,1
);


