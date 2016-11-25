drop table T_TASKS if exists;
drop sequence S_TASKS_ID if exists;

create table T_TASKS (ID integer identity primary key, NAME varchar(50) not null);
create sequence S_TASKS_ID start with 100;
