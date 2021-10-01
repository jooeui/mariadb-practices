-- inner join
-- 예제1: employees 테이블과 title 테이블을 join 하여 직원의 이름과 직책을 모두 출력
select e.first_name, t.title
from employees as e, titles as t
where e.emp_no = t.emp_no			-- join 조건(n-1)
	and t.to_date = '9999-01-01';	-- row 선택 조건
    
-- 예제2: employees 테이블과 titles 테이블을 join 하여 직원의 이름과 직책을 출력하되 여성 엔지니어만 출력 
select e.first_name, t.title
from employees e, titles t
where e.emp_no = t.emp_no			-- join 조건(n-1)
	and t.to_date = '9999-01-01'	-- row 선택 조건1
    and e.gender = 'f'				-- row 선택 조건2
    and t.title = 'engineer';		-- row 선택 조건3
    
-- ANSI/ISO SQL1999 JOIN 표준 문법
-- 	1) natural join
-- 		두 테이블에 공통 컬럼이 있으면 별다른 조인 조건 없이 암묵적으로 조인이 됨
-- 		쓸 일 없음
select e.first_name, t.title
from employees e natural join titles t
where t.to_date = '9999-01-01';	-- row 선택 조건

-- 	2) join ~ using
select e.first_name, t.title
from employees e join titles t using (emp_no)
where t.to_date = '9999-01-01';

-- 	3) join ~ on (inner join)	- 추천!
select e.first_name, t.title
from employees e join titles t on e.emp_no = t.emp_no
where t.to_date = '9999-01-01';

-- outer join
-- insert into dept values(null, '총무');
-- insert into dept values(null, '영업');
-- insert into dept values(null, '개발');
-- insert into dept values(null, '기획');
select * from dept;

-- insert into emp values(null, '둘리', 1);
-- insert into emp values(null, '마이콜', 2);
-- insert into emp values(null, '또치', 3);
-- insert into emp values(null, '길동', null);
select * from emp;

select a.name as '이름', b.name as '부서'
from emp a join dept b on a.dept_no = b.no;

select a.name as '이름', ifnull(b.name, '없음') as '부서'
from emp a left join dept b on a.dept_no = b.no;

select a.name as '이름', b.name as '부서'
from emp a right join dept b on a.dept_no = b.no;