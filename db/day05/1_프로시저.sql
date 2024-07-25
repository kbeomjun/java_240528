use university;
# 학과 코드, 이름, 전공이 주어졌을때 신입생을 등록하는 프로시저
drop procedure if exists insert_freshman;
Delimiter //
create procedure insert_freshman(
	in _major_code char(3),
    in _major varchar(15),
    in _name varchar(30)
)
begin
	declare _year int;
    declare _num char(3);
	declare _st_num char(10);
    # 학번을 생성
    # 년 + 학과코드 + 순서
    # 프로시저가 실행될 때 연도를 가져옴
    set _year = (select year(now()));
    set _num = (select lpad(count(*)+1, 3, '0') from student where st_major = _major);
    set _st_num = concat(_year, _major_code, _num);
    insert into student values(_st_num, _name, _major, 1);
end //
delimiter ;
# 프로시저 호출
call insert_freshman('160', '컴퓨터공학', '논개');
# 결과 확인
select * from student;

# 학번, 강의번호, 중간, 기말, 과제, 출석이 주어지면 학점을 계산해서 추가하는 프로시저
# 중간 40%, 기말 40%, 과제 10%, 출석 10%
drop procedure if exists update_point;
Delimiter //
create procedure update_point(
	in _st_num char(10),
    in _le_num int,
    in _mid int,
    in _final int,
    in _homework int,
    in _attendance int
)
begin
	# 최종 성적이 95점 이상 A+, 90점 이상 A, 85점 이상 B+, ..., 60미만 F
	declare _sum int;
    declare _total varchar(2);
    
    set _sum = (select _mid * 0.4 + _final * 0.4 + _homework * 0.1 + _attendance * 0.1);
    if _sum >= 95 then set _total = 'A+';
    elseif _sum >= 90 then set _total = 'A';
    elseif _sum >= 85 then set _total = 'B+';
    elseif _sum >= 80 then set _total = 'B';
    elseif _sum >= 75 then set _total = 'C+';
    elseif _sum >= 70 then set _total = 'C';
    elseif _sum >= 65 then set _total = 'D+';
    elseif _sum >= 60 then set _total = 'D';
    else set _total = 'F';
    end if;
    update course set co_mid = _mid, co_final = _final, co_homework = _homework, co_attendance = _attendance, co_total = _total
    where co_st_num = _st_num and co_le_num = _le_num;
end //
delimiter ;
# 프로시저 호출
call update_point('2024160005', 2, 90, 80, 70, 50);
# 결과 확인
select * from course;

# 프로시저 확인
show procedure status;
# 프로시저 스크립트 확인
show create procedure insert_freshman;

use cgv;
# 예매자 아이디, 예매 상영시간번호, 성인수, 청소년수가 주어지면 티켓을 예매하는 프로시저
drop procedure if exists ticketing_insert;
delimiter //
create procedure ticketing_insert(
	in _me_id varchar(15),
    in _sd_num int,
    in _adult int,
    in _teenager int
)
begin
	declare _total int;
    declare _sd_early int;
    
    set _sd_early = (select sd_early from schedule where sd_num = _sd_num);
    case _sd_early
    when 1 then set _total = _adult * (select pr_price from price where pr_type = '조조 성인') + _teenager * (select pr_price from price where pr_type = '조조 청소년');
    when 0 then set _total = _adult * (select pr_price from price where pr_type = '성인') + _teenager * (select pr_price from price where pr_type = '청소년');
    end case;
    insert into ticketing values(null, _adult, _teenager, _total, _sd_num, _me_id);
end //
delimiter ;
call ticketing_insert('abc123', 7, 2, 0);
insert into ticketing_list values(null, 4, 1), (null, 4, 2);