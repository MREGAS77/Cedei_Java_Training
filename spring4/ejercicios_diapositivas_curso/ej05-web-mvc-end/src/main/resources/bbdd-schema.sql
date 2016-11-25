drop table T_TASKS if exists;
drop sequence S_TASKS_ID if exists;

create table T_TASKS (ID bigint identity primary key, NAME varchar(50) not null);
create sequence S_TASKS_ID start with 100;

insert into T_TASKS (ID, NAME) VALUES (1, 'Task 1: Texto1');
insert into T_TASKS (ID, NAME) VALUES (2, 'Task 2: Texto2');
insert into T_TASKS (ID, NAME) VALUES (3, 'Task 3: Texto3');
insert into T_TASKS (ID, NAME) VALUES (4, 'Task 4: Texto4');
insert into T_TASKS (ID, NAME) VALUES (5, 'Task 5: Texto5');
