/*
원인
사례1 - CREATE USER SAMPLE; 쿼리가 잘못되었다.
사례2 - 계정을 생성하고 권한을 부여하지 않았다.

조치내용
사례1 - create user 'sample'@'%' identified by '1234'; 로 쿼리를 수정
사례2 - grant all privileges on 테이블명.* to 'sample'@'%'; 쿼리를 추가
*/
create user 'sample'@'%' identified by '1234';
grant all privileges on cgv.* to 'sample'@'%';

/*
원인
1. DEPT_CODE='D9' OR DEPT_CODE='D6'
2. SALARY > 3000000
3. EMAIL LIKE '____%' 
4. BONUS IS NULL
5. 남자를 선택하는 쿼리가 없다

조치내용
1. DEPT_CODE in('D9', 'D6')
2. SALARY >= 3000000
3. EMAIL LIKE '___!_%' escape '!'
4. BONUS IS not NULL
5. and emp_no like '%-1%'; 쿼리를 추가
*/
use shoppingmall;
select me_id, me_phone, me_authority, me_fail from member 
where me_authority in('USER') 
and me_fail > 0 
and me_email LIKE '___!_%' escape '!'
and me_phone is not null;
select * from member where me_id is not null;
select * from member where me_email LIKE '___!_%' escape '!';
select * from member where me_phone like '%-1%';

/*
원인
BONUS = NULL AND MANAGER_ID !=NULL;

조치내용
BONUS is NULL AND MANAGER_ID is not NULL;
*/
select * from member where me_phone = null;

/*
원인
WHERE SALARY > 2800000
- 부서가 아닌 각 인원의 월급이 280만원을 초과하는 인원을 조회하는 쿼리

조치내용
GROUP BY DEPT having avg(salary) > 2800000
- 그룹화한 부서의 평균 월급이 2800000을 초과하는 부서를 조회하는 쿼리로 수정
*/
select ca_name '카테고리', floor(avg(pr_price)) '평균가격' 
from category left join product on pr_ca_num = ca_num 
group by ca_num having avg(pr_price) > 0
order by ca_num;

/*
원인
WHERE ROWNUM <= 3;
- ROWNUM 속성이 존재하지 않음

조치내용
select *
from (SELECT ROW_NUM() over(ORDER BY SAL DESC) as ROWNUM, EMPNAME, SAL FROM EMP) as em
where ROWNUM <= 3;
- ROWNUM, EMPNAME, SAL 속성을 갖는 테이블에서 ROWNUM이 3 이하인 행만 선택하는 쿼리로 수정
*/
use community;
select * 
from (select ROW_NUMBER() over(order by po_views desc) as ROWNUM, post.* from post) as po 
where ROWNUM <= 3;