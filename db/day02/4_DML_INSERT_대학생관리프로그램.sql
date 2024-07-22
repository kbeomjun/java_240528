/*
전공
컴퓨터공학 : 160
화학공학 : 135
기계공학 : 123
*/

# 전공이 컴퓨터공학인 신입생이 5명이고, 5명을 등록하는 쿼리
# 홍길동, 임꺽정, 유재석, 둘리, 고길동
# 학번은 계산해서 넣기
insert into student(st_num, st_name, st_major) values
(2024160001, '홍길동', '컴퓨터공학'),
(2024160002, '임꺽정', '컴퓨터공학'),
(2024160003, '유재석', '컴퓨터공학'), 
(2024160004, '둘리', '컴퓨터공학'),
(2024160005, '고길동', '컴퓨터공학');

# 전공이 화학공학인 신입생이 3명 있고, 3명을 등록하는 쿼리
# 나영석, 강호동, 이둘리
insert into student(st_num, st_name, st_major) values
(2024135001, '나영석', '화학공학'),
(2024135002, '강호동', '화학공학'),
(2024135003, '이둘리', '화학공학');

# 전공이 기계공학인 신입생이 3명 있고, 3명을 등록하는 쿼리
# 가나다, 박둘리, 마둘리
insert into student(st_num, st_name, st_major) values
(2024123001, '가나다', '기계공학'),
(2024123002, '박둘리', '기계공학'),
(2024123003, '마둘리', '기계공학');

# 신입교수 박교수 3관 101호 컴퓨터공학
# 신입교수 이교수 3관 201호 화학공학
# 신입교수 김교수 3관 301호 기계공학
insert into professor(pr_num, pr_name, pr_major, pr_room) values
(2024160001, '박교수', '컴퓨터공학', '3관 101호'),
(2024135001, '이교수', '화학공학', '3관 201호'),
(2024123001, '김교수', '기계공학', '3관 301호');

# 강의 등록
# 컴퓨터개론, 전공 필수, 1관 101호, 월1A,1B,2A,2B, 2, 2, 202416001
# 프로그래밍언어, 전공 필수, 1관 101호 월5A,5B,6A,6B,수5A,5B,6A,6B, 3, 4, 202416001
# 화학기초, 전공 필수, 2관 101호, 화1A,1B,2A,2B,금1A,1B,2A,2B, 3, 4, 2024135001
# 동역학, 전공 필수, 4관 101호,  수1A,1B,2A,2B,3A,3B,4A,4B, 3, 4, 2024123001
insert into lecture values
(null, '컴퓨터개론', '전공 필수', '1관 101호', '월1A,1B,2A,2B', 2, 2, 2024160001),
(null, '프로그래밍언어', '전공 필수', '1관 101호', '월5A,5B,6A,6B,수5A,5B,6A,6B', 3, 4, 2024160001),
(null, '화학기초', '전공 필수', '2관 101호', '화1A,1B,2A,2B,금1A,1B,2A,2B', 3, 4, 2024135001),
(null, '동역학', '전공 필수', '4관 101호', '수1A,1B,2A,2B,3A,3B,4A,4B', 3, 4, 2024123001);

# 수강 등록
# 컴공 고길동,임꺽정 학생은 컴퓨터개론, 프로그래밍언어를 수강신청
# 컴공 둘리,홍길동 학생은 컴퓨터개론을 수강신청
# 컴공 유재석 학생은 프로그래밍언어를 수강신청
# 화공 강호동, 나영석 학생은 화학기초를 수강신청
# 기계 가나다, 마둘리, 박둘리 학생은 동역학을 수강신청
# 기계 가나다 학생은 프로그래밍언어를 수강신청
insert into course(co_st_num, co_le_num) values
(2024160005, 1), (2024160005, 2), (2024160002, 1), (2024160002, 2),
(2024160004, 1), (2024160001, 1),
(2024160003, 2),
(2024135002, 3), (2024135001, 3),
(2024123001, 4), (2024123003, 4), (2024123002, 4),
(2024123001, 2);