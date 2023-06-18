-- 刪除資料表
drop table if exists job;
drop table if exists employee;

-- 建立 employee 資料表
create table if not exists employee (
	eid integer not null auto_increment,
	ename varchar(50) not null unique,
	salary integer,
	createtime timestamp default current_timestamp,
	primary key (eid)
);
-- 建立 job 資料表
create table if not exists job (
	jid integer not null auto_increment,
	jname varchar(50) not null unique,
	eid integer not null,
	foreign key (eid) references employee(eid), -- 外鍵關聯
	primary key (jid)
);

-- 建立 Employee 範例資料
insert into employee(ename, salary) values('John', 40000);
insert into employee(ename, salary) values('Mary', 50000);
insert into employee(ename, salary) values('Bobo', 60000);
insert into employee(ename, salary) values('Mark', 70000);

-- 建立 Job 範例資料
insert into job(jname, eid) values('Job A', 1);
insert into job(jname, eid) values('Job B', 1);
insert into job(jname, eid) values('Job C', 2);
insert into job(jname, eid) values('Job D', 2);
insert into job(jname, eid) values('Job E', 4);
insert into job(jname, eid) values('Job F', 4);
insert into job(jname, eid) values('Job G', 4);