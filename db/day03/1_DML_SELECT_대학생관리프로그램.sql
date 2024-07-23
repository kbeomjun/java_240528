use university;
# 컴퓨터공학 고길동 학생이 수강신청한 강의의 개수를 조회하는 쿼리
SELECT 
    st_name '이름', st_major '전공', COUNT(co_num) '강의 개수'
FROM
    course
	JOIN student ON co_st_num = st_num
WHERE st_name = '고길동' and st_major = '컴퓨터공학';

# 신입생을 조회하는 쿼리
SELECT 
    st_num '학번', st_name '이름'
FROM
    student
WHERE st_num LIKE '2024______' and st_grade = 1;

# 각 전공별 학생 수를 조회하는 쿼리
select st_major '전공', count(*) '학생 수(명)' from student group by st_major;

# 컴퓨터공학 고길동 학생이 수강신청한 학점을 조회하는 쿼리
SELECT 
    st_name '이름', st_major '전공', SUM(le_point) '학점'
FROM
    course
    join student on st_num = co_st_num
    join lecture on le_num = co_le_num
where st_name = '고길동' and st_major = '컴퓨터공학';

# 강의별 수강신청한 학생수를 조회하는 쿼리
SELECT 
    le_title '강의명', count(co_st_num) '수강생(명)'
FROM
    course
    join lecture on le_num = co_le_num
group by le_title;

# 학생이 있는 학과 이름을 조회하는 쿼리
select distinct st_major from student;
SELECT 
    st_major '전공', count(st_major) '학생수(명)'
FROM
    student
group by st_major having count(st_major) >= 1;

# 홍길동 학생이 수강하는 강의 목록을 조회하는 쿼리
SELECT 
    st_name '학생', le_title '강의'
FROM
    course
    join student on st_num = co_st_num
    join lecture on le_num = co_le_num
where st_name = '홍길동';

# 김교수가 강의하는 강의명을 조회
SELECT
    pr_name '교수명', le_title '강의명'
FROM
    lecture
    join professor on pr_num = le_pr_num
where pr_name = '김교수';