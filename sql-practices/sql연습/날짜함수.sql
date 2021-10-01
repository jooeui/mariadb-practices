-- 함수: 날짜 함수

-- CURDATE(), CURRENT_DATE	# 날짜
select curdate(), current_date;

-- CURTIME(), CURRENT_TIME	# 시간
select curtime(), current_time();

-- now() vs sysdate()	# 날짜 + 시간
select now(), sysdate();
select now(), sleep(2), now();	-- 쿼리가 시작된 시간
select sysdate(), sleep(2), sysdate();	-- sysdate()가 실행 됐을 때 시간


-- date_format(date, format)
select date_format(now(), '%Y-%m-%d %h:%i:%s');		-- 기본 포맷
select date_format(now(), '%Y/%m/%d %h:%i:%s');
select date_format(now(), '%Y년 %M월 %d일 %h시 %i분 %s초');		-- %M: 월 영어
select date_format(now(), '%Y년 %c월 %d일 %h시 %i분 %s초');		-- %c: 월 숫자

-- period_diff
-- YYMM, YYYYMM
-- 예) 근무 개월 수를 출력
select first_name,
		period_diff(date_format(curdate(), '%Y%m'), date_format(hire_date, '%Y%m')) as month
from employees
order by month;

-- date_add(= adddate), data_sub(= subdate)
-- 날짜를 date에 type(day, month, year) 형식으로 더하거나 뺀다.
-- 예제: 각 사원들의 근무 년수가 5년이 되는 날은 언제인가?
select first_name,
		hire_date,
        date_add(hire_date, interval 5 year)
from employees;

-- cast
select '12345' + 10, cast('12345' as int) + 5;
select date_format(cast('2021-10-10' as date), '%Y-%m-%d');
select cast(1-2 as unsigned);
select cast(cast(1-2 as unsigned) as signed);

/* mysql type
	- varchar, char, text, CLOB(Character Large OBject)
    - signed(unsigned) [integer], int(integer), medium int, big int, int(11)
    -- float, double
    -- time, date, datetime
    -- LOB: CLOB, BLOB(멜티미디어 DB 만들 때 많이 사용)