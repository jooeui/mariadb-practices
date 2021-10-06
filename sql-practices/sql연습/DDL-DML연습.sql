drop table member;
create table member(
	no int(11) not null auto_increment,
    email varchar(200) not null,
    password varchar(64) not null,
	name varchar(100) not null,
    department varchar(100),
    primary key(no)
);

alter table member add juminbunho char(13) not null after email;
desc member;
alter table member drop juminbunho;
desc member;
alter table member add join_date datetime not null;
desc member;
alter table member change deparment department varchar(100) not null;
desc member;

select @@AUTOCOMMIT;
set autocommit=0;

delete from member;

-- insert
insert into member 
values(null, 'kje_0727@naver.com', password('1234'), '김주의', '개발팀', now(), null);

insert into member (no, email, password, department, name, join_date)
values(null, 'kje_07271@naver.com', password('1234'), '개발팀1', '김주의1', now());

select * from member;