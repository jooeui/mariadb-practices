-- Basic Query

create table pet(
	name varchar(20),
    onwer varchar(20),
    sepecies varchar(20),
    gender char(1),
    birth DATE,
    death DATE
);

-- 테이블 구조 확인
desc pet;