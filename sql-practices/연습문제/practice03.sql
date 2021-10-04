-- practice03

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '연봉'
from employees e join salaries s on e.emp_no=s.emp_no
where s.to_date='9999-01-01'
order by s.salary desc;


-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as Name, t.title as '직책'
from employees e join titles t on e.emp_no=t.emp_no
where t.to_date='9999-01-01'
order by Name asc;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요.
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as Name, d.dept_name as '부서'
from employees e 
	join dept_emp de on e.emp_no=de.emp_no and de.to_date='9999-01-01'
	join departments d on de.dept_no=d.dept_no
order by Name asc;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as Name, s.salary as '연봉', t.title as '직책', d.dept_name as '부서'
from employees e
	join salaries s on e.emp_no=s.emp_no and s.to_date='9999-01-01'
	join titles t on e.emp_no=t.emp_no and t.to_date='9999-01-01'
    join dept_emp de on e.emp_no=de.emp_no and de.to_date='9999-01-01'
    join departments d on de.dept_no=d.dept_no
order by Name asc;


-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. 
-- (현재 ‘Technique Leader’의 직책으로 근무하는 사원은 고려하지 않습니다.)
-- 이름은 first_name과 last_name을 합쳐 출력 합니다.
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as Name
from employees e
	join titles t on e.emp_no=t.emp_no
where title='Technique Leader' and to_date!='9999-01-01'
order by e.emp_no asc;


-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select concat(e.first_name, ' ', e.last_name) as Name, d.dept_name as '부서명', t.title as '직책'
from employees e
	join titles t on e.emp_no=t.emp_no and t.to_date='9999-01-01'
    join dept_emp de on e.emp_no=de.emp_no and de.to_date='9999-01-01'
    join departments d on de.dept_no=d.dept_no
where e.last_name LIKE 'S%'
order by Name asc;


-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
select s.emp_no as '사원번호', s.salary as '현재 급여'
from employees e
	join titles t on e.emp_no=t.emp_no and t.to_date='9999-01-01'
	join salaries s on t.emp_no=s.emp_no and s.to_date='9999-01-01'
where title='Engineer' and s.salary >= 40000
order by s.salary desc;


-- 문제8.
-- 현재 급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오
-- 풀이1) 현재 급여가 50000이 넘는 사원의 직책과 급여 출력(현재 급여 50000이 넘는 것 모두 출력)
select t.title as '직책', s.salary as '현재 급여'
from employees e
	join titles t on e.emp_no=t.emp_no and t.to_date='9999-01-01' 
	join salaries s on e.emp_no=s.emp_no and s.to_date='9999-01-01'
where s.salary > 50000
order by s.salary desc;

-- 풀이2) 급여가 50000이 넘는 직책 중 가장 높은 급여를 받는 사원의 직책, 급여 출력(직책에서 현재 급여가 가장 높은 것을 출력)
select t.title as '직책', max(s.salary) as '현재 급여'
from employees e
	join titles t on e.emp_no=t.emp_no and t.to_date='9999-01-01' 
	join salaries s on e.emp_no=s.emp_no and s.to_date='9999-01-01'
where s.salary > 50000
group by t.title
order by max(s.salary) desc;


-- 문제9.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select d.dept_name as '부서', avg(s.salary) as '평균 연봉'
from employees e
	join dept_emp de on e.emp_no=de.emp_no and de.to_date='9999-01-01'
    join departments d on de.dept_no=d.dept_no
    join salaries s on e.emp_no=s.emp_no and s.to_date='9999-01-01'
group by de.dept_no
order by avg(s.salary) desc;


-- 문제10.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select t.title as '직책', avg(s.salary) as '평균 연봉'
from employees e
    join titles t on e.emp_no=t.emp_no and t.to_date='9999-01-01'
    join salaries s on e.emp_no=s.emp_no and s.to_date='9999-01-01'
group by t.title
order by avg(s.salary) desc;

