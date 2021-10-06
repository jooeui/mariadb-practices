-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
-- 1) 현재 평균 연봉
select avg(salary) from salaries where to_date='9999-01-01';

-- 2) 현재 평균보다 많은 월급을 받는 직원 수
select count(*) as '평균 연봉 이상 직원 수'
from salaries
where to_date='9999-01-01'
	and salary > (select avg(salary)
					from salaries
                    where to_date='9999-01-01');


-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
-- 1) 부서별 최고 급여 및 부서 평균 연봉
select de.dept_no, max(s.salary) as max_salary, avg(s.salary) as avg_salary
from dept_emp de, salaries s
where de.emp_no = s.emp_no
	and de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by de.dept_no;

-- 2) 부서별 최고 급여 사원 정보 및 부서 연봉
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', sub1.avg_salary as '부서 평균 연봉'
from employees e,
	 dept_emp de,
     salaries s,
     (select de.dept_no, max(s.salary) as max_salary, avg(s.salary) as avg_salary
	  from dept_emp de, salaries s
	  where de.emp_no = s.emp_no
		 and de.to_date = '9999-01-01'
		 and s.to_date = '9999-01-01'
	  group by de.dept_no) sub1
where e.emp_no = de.emp_no
	and de.emp_no = s.emp_no
    and sub1.dept_no = de.dept_no
    and sub1.max_salary = s.salary
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
order by s.salary desc;


-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
-- 1) 부서 평균 급여
select de.dept_no, avg(s.salary) as avg_salary
from dept_emp de, salaries s
where de.emp_no = s.emp_no
	and de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by de.dept_no;

-- 2) 부서 평균 급여보다 연봉이 높은 사원
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '연봉'
from employees e, 
	 salaries s, 
     dept_emp de,
     (select de.dept_no, avg(s.salary) as avg_salary
		from dept_emp de, salaries s
		where de.emp_no = s.emp_no
			and de.to_date = '9999-01-01'
			and s.to_date = '9999-01-01'
		group by de.dept_no) sub1
where e.emp_no = s.emp_no
	and s.emp_no = de.emp_no
    and de.dept_no = sub1.dept_no
	and s.to_date = '9999-01-01'
	and de.to_date = '9999-01-01'
    and s.salary > sub1.avg_salary;


-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
-- 1) 매니저 이름 출력
select dm.dept_no, d.dept_name, concat(e.first_name, ' ', e.last_name) as manager_name
from dept_manager dm, employees e, departments d
where dm.emp_no = e.emp_no
	and dm.dept_no = d.dept_no
	and dm.to_date = '9999-01-01';

-- 2) 사원 정보 및 매니저 이름
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '사원 이름', dm.manager_name as '매니저 이름', d.dept_name as '부서 이름'
from employees e,
	 dept_emp de,
	 departments d,
     (select dm.dept_no, concat(e.first_name, ' ', e.last_name) as manager_name
		from dept_manager dm, employees e
		where dm.emp_no = e.emp_no
			and dm.to_date = '9999-01-01') dm
where e.emp_no = de.emp_no
	and de.dept_no = d.dept_no
    and d.dept_no = dm.dept_no
    and de.to_date = '9999-01-01';


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
-- 1) 부서별 평균 연봉
select de.dept_no, avg(s.salary) as avg_salary
from salaries s, dept_emp de
where s.emp_no = de.emp_no
	and s.to_date = '9999-01-01'
	and de.to_date = '9999-01-01'
group by de.dept_no
order by avg(s.salary) desc;

-- 2-1) top-k
-- 평균연봉이 가장 높은 부서
select de.dept_no, avg(s.salary) as avg_salary
from salaries s, dept_emp de
where s.emp_no = de.emp_no
	and s.to_date = '9999-01-01'
	and de.to_date = '9999-01-01'
group by de.dept_no
order by avg(s.salary) desc
limit 1;
-- 3-1) 평균 연봉이 가장 높은 부서의 사원 정보
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', t.title as '직책', s.salary as '연봉'
from employees e, 
	 titles t, 
     salaries s, 
     dept_emp de,
     (select de.dept_no, avg(s.salary) as avg_salary
		from salaries s, dept_emp de
		where s.emp_no = de.emp_no
			and s.to_date = '9999-01-01'
			and de.to_date = '9999-01-01'
		group by de.dept_no
		order by avg(s.salary) desc
		limit 1) sub1
where e.emp_no = t.emp_no
	and e.emp_no = s.emp_no
    and e.emp_no = de.emp_no
    and de.dept_no = sub1.dept_no
    and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and de.to_date = '9999-01-01'
order by s.salary asc;

-- 2-1) subquery 2개
-- 평균연봉이 가장 높은 부서
select ad.dept_no, max(avg_salary)
from ( select de.dept_no, avg(s.salary) as avg_salary
		from salaries s, dept_emp de
		where s.emp_no = de.emp_no
			and s.to_date = '9999-01-01'
			and de.to_date = '9999-01-01'
		group by de.dept_no
		order by avg(s.salary) desc) ad;
-- 3-1) 평균 연봉이 가장 높은 부서의 사원 정보
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', t.title as '직책', s.salary as '연봉'
from employees e, 
	 titles t, 
     salaries s, 
     dept_emp de,
     (select ad.dept_no, max(avg_salary)
		from ( select de.dept_no, avg(s.salary) as avg_salary
				from salaries s, dept_emp de
				where s.emp_no = de.emp_no
					and s.to_date = '9999-01-01'
					and de.to_date = '9999-01-01'
				group by de.dept_no
				order by avg(s.salary) desc) ad) md
where e.emp_no = t.emp_no
	and e.emp_no = s.emp_no
    and e.emp_no = de.emp_no
    and de.dept_no = md.dept_no
    and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and de.to_date = '9999-01-01'
order by s.salary asc;


-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
-- 풀이1) top-k
select dept_name as '평균 연봉 가장 높은 부서'
from departments d,
	 (select de.dept_no, avg(s.salary) as avg_salary
		from salaries s, dept_emp de
		where s.emp_no = de.emp_no
			and s.to_date = '9999-01-01'
			and de.to_date = '9999-01-01'
		group by de.dept_no
		order by avg(s.salary) desc
		limit 1) sub1
where d.dept_no = sub1.dept_no;

-- 풀이2) subquery 2개
select d.dept_name as '평균 연봉 가장 높은 부서'
from departments d,
     (select ad.dept_no, max(avg_salary)
		from ( select de.dept_no, avg(s.salary) as avg_salary
				from salaries s, dept_emp de
				where s.emp_no = de.emp_no
					and s.to_date = '9999-01-01'
					and de.to_date = '9999-01-01'
				group by de.dept_no
				order by avg(s.salary) desc) ad) md
where d.dept_no = md.dept_no;


-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- 1) 직책별 평균 연봉
select t.title, avg(s.salary) as avg_salary
from salaries s, titles t
where s.emp_no = t.emp_no
	and s.to_date = '9999-01-01'
	and t.to_date = '9999-01-01'
group by t.title
order by avg_salary desc;

-- 2) 평균 연봉이 가장 높은 직책
-- 2-1) top-k
select distinct t.title as '평균 연봉 가장 높은 직책'
from titles t,
	 (select t.title, avg(s.salary) as avg_salary
		from salaries s, titles t
		where s.emp_no = t.emp_no
			and s.to_date = '9999-01-01'
			and t.to_date = '9999-01-01'
		group by t.title
		order by avg_salary desc
		limit 1) sub1
where t.title = sub1.title;

-- 2-2) subquery 2개
select distinct t.title as '평균 연봉 가장 높은 직책'
from titles t,
     (select t1.title, max(avg_salary)
		from ( select t.title, avg(s.salary) as avg_salary
				from salaries s, titles t
				where s.emp_no = t.emp_no
					and s.to_date = '9999-01-01'
					and t.to_date = '9999-01-01'
				group by t.title
				order by avg_salary desc) t1) mt
where t.title = mt.title;


-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
-- 1) 매니저 이름, 연봉
select dm.dept_no, concat(e.first_name, ' ', e.last_name) as manager_name, s.salary
from dept_manager dm, employees e, salaries s
where dm.emp_no = e.emp_no
	and dm.emp_no = s.emp_no
	and dm.to_date = '9999-01-01'
	and s.to_date = '9999-01-01';
    
-- 2) 매니저보다 연봉이 높은 사원의 정보
select d.dept_name as '부서이름', concat(e.first_name, ' ', e.last_name) as '사원이름', s.salary as '연봉', 
	   dms.manager_name as '매니저 이름', dms.salary as '매니저 연봉'
from dept_emp de,
	 departments d,
	 employees e, 
     salaries s, 
     (select dm.dept_no, concat(e.first_name, ' ', e.last_name) as manager_name, s.salary
		from dept_manager dm, employees e, salaries s
		where dm.emp_no = e.emp_no
			and dm.emp_no = s.emp_no
			and dm.to_date = '9999-01-01'
			and s.to_date = '9999-01-01') dms
where de.dept_no = d.dept_no
	and de.emp_no = e.emp_no
    and e.emp_no = s.emp_no
    and de.dept_no = dms.dept_no
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and s.salary > dms.salary
order by d.dept_name, e.first_name, e.last_name, s.salary;