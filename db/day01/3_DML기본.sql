# insert
# 한 행(레코드)의 데이터를 추가할때 사용
# insert into 테이블명(속성1, ..., 속성n) value(값1, ..., 값n);
# value는 한 번에 한 행을 추가할때 사용
# 속성의 개수와 값의 개수가 같아야 함
insert into student.student(studentNum, grade, class, num, name)
value(null, 1, 1, 1, '홍길동');

# insert into 테이블명(속성1, ..., 속성n) values(값1, ..., 값n),(값1, ..., 값n);
# values는 한 번에 한 개 이상의 행을 추가할때 사용
# 속성의 개수와 값의 개수가 같아야 함
insert into student.student(grade, class, num, name)
values(1, 1, 2, '임꺽정'),(1, 1, 3, '고길동');

# insert into 테이블명 value(값1, ..., 값n);
# 테이블명 옆에 속성명을 생략한 경우 table 생성시 추가했던 속성 순서대로 값들을 넣어주어야 함
# 전체 속성 개수만큼 값들을 넣어줘야 함
insert into student.student value(null, 1, 1, 4, '둘리');

# update
# 특정 행들의 값들을 변경할때 사용
# update 테이블명 set 속성명1 = 값1, ..., 속성명n = 값n where 조건;
# = : 같다, != : 다르다, <> : 다르다
# is null : null이면, is not null : null이 아니면
# and : ~하고, or : ~이거나
# and와 or의 우선순위가 다름, and가 더 높음
# 날짜는 'yyyy-MM-dd hh:mm:ss' 형태의 문자열과 비교 가능하고 =, >=, <=, !=를 활용할 수 있음
update student set name = '또치' where student.studentNum = 2;

# 워크 벤치에서는 안전하게 수정/삭제할 수 있게하기 위해 기본키가 아닌 조건으로 수정/삭제하는 것을 막음
# 해결방법 : Edit > Preferences... > SQL Editor > Safe Updates 체크박스 해제 후 ok 클릭
UPDATE student.student 
SET 
    name = '마이콜'
WHERE
    student.grade = 1 and student.class = 1 and student.num = 2;
    
# delete
# 특정 행들을 삭제할때 사용
# delete from 테이블명 where 조건;
delete from student.student where student.studentNum = 4;

delete from student.student where grade = 1 and class = 1 and num = 3;

delete from student.student;

# delete와 truncate의 차이
# delete는 데이터만 지우고 초기 설정은 그대로 유지 - auto_increment 시작 숫자 유지
# truncate은 데이터뿐만 아니라 설정도 초기로 돌림 - auto_increment 숫자가 1에서 시작
# truncate은 다른 테이블이 참조하고 있는 경우 초기화가 안됨
insert into student.student(grade, class, num, name) value(1, 1, 1, '홍길동');
insert into student.subject(grade, semester, name) value(1, 1, '국어');
insert into student.score(midTerm, finalTerm, performance, studentNum, subjectNum) value(100, 90, 90, 1, 1);
delete from student.score;
insert into student.score(midTerm, finalTerm, performance, studentNum, subjectNum) value(100, 90, 90, 1, 1);
truncate table student.score;
insert into student.score(midTerm, finalTerm, performance, studentNum, subjectNum) value(100, 90, 90, 1, 1);