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


-- 실습문제1
-- 현재 회사 상황을 반영한 직원별 근무 부서를 사번, 이름, 근무 부서로 출력해보세요.
select e.emp_no as '사번', 
		concat(e.first_name, ' ', e.last_name) as '이름',
        d.dept_name as '근무 부서'
from employees e, dept_emp de, departments d
where e.emp_no=de.emp_no
	and de.dept_no=d.dept_no
	and de.to_date='9999-01-01'
order by e.emp_no asc;

-- 실습문제2
-- 현재 회사에서 지금되고 있는 급여체계를 반영한 결과를 출력하세요.
-- 사번, 이름, 연봉 형태로 출력하세요.
select e.emp_no as '사번', 
		concat(e.first_name, ' ', e.last_name) as '이름',
        s.salary as '연봉'
from employees e, salaries s
where e.emp_no=s.emp_no
	and s.to_date='9999-01-01'
order by s.salary desc;

-- 예제: 현재 직책별로 평균 연봉과 인원수를 구하되, 직책별로 인원이 100명 이상인 직책만 출영
select t.title, avg(s.salary), count(*)
from titles t, salaries s
where t.emp_no=s.emp_no
	and t.to_date='9999-01-01'
    and s.to_date='9999-01-01'
group by t.title
having count(*) >= 100
order by avg(salary) desc;

-- 예제6: 현재 부서별로 현재 직책이 Engineer인 직원들에 대해서만 평균 급여를 구하세요.
select d.dept_name, avg(s.salary)
from dept_emp de, salaries s, titles t, departments d
where de.emp_no = s.emp_no
	and s.emp_no = t.emp_no
    and de.dept_no = d.dept_no
    and de.to_date='9999-01-01'
    and de.to_date='9999-01-01'
    and s.to_date='9999-01-01'
    and t.to_date='9999-01-01'
    and t.title = 'Engineer'
group by de.dept_no;

-- 예제7: 현재 직책별로 급여의 총합을 구하되 Engineer 직책은 제외하세요.
-- 단, 총합이 2,000,000,000 이상인 직책만 나타내며 급여총합에 대해서 내림차순(DESC)로 정렬하세요.
select t.title, sum(s.salary) as sum_salary
from titles t, salaries s
where t.emp_no = s.emp_no
	and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and t.title != 'Engineer'
group by t.title
having sum(s.salary) >= 2000000000
order by sum_salary desc;