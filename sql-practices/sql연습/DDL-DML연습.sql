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
set autocommit=1;

delete from member;

-- insert
insert into member 
values(null, 'kje_0727@naver.com', password('1234'), '김주의', '개발팀', now(), null);
select * from member;

insert into member (no, email, password, department, name, join_date)
values(null, 'kje_07271@naver.com', password('1234'), '개발팀2', '김주의2', now());
select * from member;

-- update
update member
set email='kje0727@naver.com', password=password('3456')
where no=2;
select * from member;

-- delete
delete from member
where no=1;
select * from member;

-- transaction
select @@AUTOCOMMIT;
set autocommit=0;		-- autocommit이 0이면 실제 DB에 바로 commit이 안 됨!

insert into member (no, email, password, department, name, join_date)
values(null, 'kje_07274@naver.com', password('1234'), '개발팀4', '김주의4', now());
select * from member;

commit;
select * from member;

set autocommit=1;