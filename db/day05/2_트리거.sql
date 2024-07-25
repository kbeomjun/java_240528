use cgv;
# 상영시간에 예약가능한 초기 좌석수를 상영관 좌석수로 수정
update schedule join screen on sc_num = sd_sc_num
set sd_possible = sc_seat;

# 예약된 좌석만큼 상영시간에서 예약가능한 좌석수를 빼서 수정하는 쿼리
# 1번 스케쥴에 예매된 좌석수만큼 1번 스케쥴의 좌석을 수정하는 쿼리
update schedule
set sd_possible = sd_possible - (select sum(ti_adult + ti_teenager) from ticketing where ti_sd_num = 1)
where sd_num = 1;

# 예매가 발생하면 예매한 상영에서 예약 가능한 좌석수를 수정하는 트리거
drop trigger if exists ticketing_insert;
delimiter //
create trigger ticketing_insert
after insert on ticketing for each row
begin
	
    # 예매된 성인수와 청소년수만큼 좌석수를 수정
    update schedule
	set sd_possible = sd_possible - (new.ti_adult + new.ti_teenager)
	where sd_num = new.ti_sd_num;
end //
delimiter ;

insert into ticketing values(null, 2, 2, 48000, 2, 'abc123');
insert into ticketing_list values(null, 2, 1), (null, 2, 2), (null, 2, 3), (null, 2, 4);

show triggers;