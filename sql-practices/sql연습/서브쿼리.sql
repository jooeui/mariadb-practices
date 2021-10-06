-- subquery
-- 1) from 절의 서브쿼리
select now() as n, sysdate() as b, 3+1 as c;

select * 
from (select now() as n, sysdate() as b, 3+1 as c) s;

-- 2) where 절의 서브쿼리
-- 예제
-- 현재 Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력해보세요.
select de.dept_no
from employees e, dept_emp de 
where e.emp_no=de.emp_no 
	and de.to_date='9999-01-01'
    and concat(e.first_name, ' ', e.last_name) = 'Fai Bale';

select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름'
from employees e, dept_emp de
where e.emp_no=de.emp_no
	and de.to_date = '9999-01-01'
    and de.dept_no = (select de.dept_no
					from employees e, dept_emp de 
					where e.emp_no=de.emp_no 
						and de.to_date='9999-01-01'
						and concat(e.first_name, ' ', e.last_name) = 'Fai Bale');
											
-- 2-1) 단일행 연산자: =, >, <, >=, <=, <>, !=		# 복수행이 나오면 에러!
-- 실습문제1
-- 현재 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름, 급여를 출력하세요.
select avg(salary)
from salaries
where to_date='9999-01-01';

select concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '급여'
from employees e, salaries s
where e.emp_no = s.emp_no
	and s.to_date='9999-01-01'
    and s.salary < (select avg(salary) from salaries where to_date='9999-01-01')
order by s.salary desc;

-- 실습문제2
-- 현재 가장 적은 평균 급여의 직책과 평균 급여를 출력하세요.
-- 예시) Engineer 2000
-- min_avg_salary

-- 1) 직책별 평균 급여
select t.title, avg(s.salary)
from salaries s, titles t
where s.emp_no = t.emp_no 
	and s.to_date = '9999-01-01'
	and t.to_date = '9999-01-01'
group by t.title
order by avg(s.salary) asc;

-- 2) 풀이1 - 가장 적은 평균 급여
select min(avg_salary)
from ( select t.title, avg(s.salary) as avg_salary
		from salaries s, titles t
		where s.emp_no = t.emp_no 
			and s.to_date = '9999-01-01'
			and t.to_date = '9999-01-01'
		group by t.title) t1;
        
select t.title, avg(s.salary)
from salaries s, titles t
where s.emp_no = t.emp_no 
	and s.to_date = '9999-01-01'
	and t.to_date = '9999-01-01'
group by t.title
having avg(s.salary) = (select min(avg_salary)
						from (select t.title, avg(s.salary) as avg_salary
								from salaries s, titles t
								where s.emp_no = t.emp_no 
									and s.to_date = '9999-01-01'
									and t.to_date = '9999-01-01'
								group by t.title) t1);
                                
-- 3) 풀이2 - 가장 적은 평균 급여 # top-k
select t.title, avg(s.salary)
from salaries s, titles t
where s.emp_no = t.emp_no 
	and s.to_date = '9999-01-01'
	and t.to_date = '9999-01-01'
group by t.title
order by avg(s.salary) asc
limit 1;

-- 2-2) 복수행 연산자: in, not in, any, all

-- any 사용법
-- 1. =any : 
-- 2. >any, >=any : 최솟값
-- 3. <any, <=any : 최댓값
-- 4. <>any : not in 동일
/* 
 * select ,,, where price in (1, 
 *							  10, 
 *							  20); 
 */
 
-- all 사용법
-- 1. =all (x)
-- 2. >all, >=all : 최댓값 
-- 3. <all, <=all : 최솟값
 
-- 실습문제3: 현재 급여가 50000 이상인 직원의 이름을 출력하세요.
/*	[출력 예시]
 *	주이 50001
 *	둘리 50002
 */
 -- 풀이1) join
select concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '급여'
from employees e, salaries s
where e.emp_no = s.emp_no
	and s.to_date = '9999-01-01'
    and s.salary > 50000
order by s.salary asc;

-- 풀이2) subquery(in)
select emp_no, salary 
from salaries 
where to_date='9999-01-01' 
	and salary > 50000;
    
select concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '급여' 
from employees e, salaries s
where e.emp_no = s.emp_no
	and s.to_date = '9999-01-01'
    and (e.emp_no, s.salary) in ( select emp_no, salary 
									from salaries 
									where to_date='9999-01-01' 
									and salary > 50000);
                                    
-- 풀이3) subquery(=any)
select concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '급여' 
from employees e, salaries s
where e.emp_no = s.emp_no
	and s.to_date = '9999-01-01'
    and (e.emp_no, s.salary) = any( select emp_no, salary 
									from salaries 
									where to_date='9999-01-01' 
									and salary > 50000);
                                    
-- 실습문제4: 현재 각 부서별로 최고 월급을 받는 직원의 이름과 월급을 출력하세요.
/*	[출력 예시]
 *	주이 40000
 *	둘리 50000
 */
/*	d004 40000
 *	d005 50000
 */
select de.dept_no, max(s.salary) as max_salary
from dept_emp de, salaries s
where de.emp_no = s.emp_no
	and de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by de.dept_no;

-- 풀이1: where subquery =any(in)
select d.dept_name as '부서', concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '급여'
from dept_emp de, salaries s, employees e, departments d
where de.emp_no = s.emp_no
	and s.emp_no = e.emp_no
    and de.dept_no = d.dept_no
	and de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
    and (de.dept_no, s.salary) in ( select de.dept_no, max(s.salary) as max_salary
									from dept_emp de, salaries s
									where de.emp_no = s.emp_no
										and de.to_date = '9999-01-01'
										and s.to_date = '9999-01-01'
									group by de.dept_no);
                                    
-- 풀이1: from subquery
select d.dept_name as '부서', concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '급여'
from dept_emp de, 
	 salaries s, 
     employees e, 
     departments d,
	 (select de.dept_no, max(s.salary) as max_salary
	  from dept_emp de, salaries s
	  where de.emp_no = s.emp_no
		and de.to_date = '9999-01-01'
		and s.to_date = '9999-01-01'
	  group by de.dept_no) sub1
where de.emp_no = s.emp_no
	and s.emp_no = e.emp_no
    and de.dept_no = d.dept_no
    and de.dept_no = sub1.dept_no
	and de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
    and s.salary = sub1.max_salary;