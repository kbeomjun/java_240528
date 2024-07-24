# 데드풀과 울버린 영화의 강남 CGV에서 7/27에 상영하는 상영시간표를 조회하는 쿼리
SELECT 
    th_name '극장', sc_name '상영관', mo_title '영화', sd_date '날짜', sd_time '시간'
FROM
    schedule
    join screen on sc_num = sd_sc_num
    join theater on th_num = sc_th_num
    join movie on mo_num = sd_mo_num
where th_name = '강남 CGV' and mo_title = '데드풀과 울버린' and sd_date = '2024-07-27';
# 서브쿼리 이용
SELECT 
    th_name '극장', sc_name '상영관', mo_title '영화', sd_date '날짜', sd_time '시간'
FROM
    schedule
    join screen on sc_num = sd_sc_num
    join (select * from theater where th_name = '강남 CGV') th on th_num = sc_th_num
    join (select * from movie where mo_title = '데드풀과 울버린') mo on mo_num = sd_mo_num
where sd_date = '2024-07-27';

# 강남 CGV 1관의 좌석을 조회하는 쿼리
SELECT 
    th_name '강남 CGV', sc_name '1관', se_name '좌석'
FROM
    seat
    join screen on sc_num = se_sc_num
    join theater on th_num = sc_th_num
where th_name = '강남 CGV' and sc_name = '1관';

# abc123회원이 예약한 예매 내역을 조회하는 쿼리(영화 제목, 극장 이름, 상영관 이름, 시간, 좌석번호)
SELECT 
    mo_title '제목', th_name '극장', sc_name '상영관', sd_date '날짜', sd_time '시간', se_name '좌석'
FROM
    ticketing_list
    join ticketing on ti_num = tl_ti_num
    join seat on se_num = tl_se_num
    join member on me_id = ti_me_id
    join schedule on sd_num = ti_sd_num
    join screen on sc_num = sd_sc_num
    join movie on mo_num = sd_mo_num
    join theater on th_num = sc_th_num
where me_id = 'abc123';

# 데드풀과 울버린 강남 CGV 1관 7/27 10:00에 예약된 좌석을 조회하는 쿼리
select 
	mo_title '제목', th_name '극장', sc_name '상영관', sd_date '날짜', sd_time '시간', se_name '예약된 좌석'
from
	seat
    join (select * from screen where sc_name = '1관') sc on sc_num = se_sc_num
    join (select * from theater where th_name = '강남 CGV') th on th_num = sc_th_num
    join (select * from schedule where sd_date = '2024-07-27' and sd_time = '10:00') sd on sd_sc_num = sc_num
    join (select * from movie where mo_title = '데드풀과 울버린') mo on mo_num = sd_mo_num
where se_name in(SELECT 
						se_name
					FROM
						ticketing_list
						join ticketing on ti_num = tl_ti_num
						join seat on se_num = tl_se_num);
# 1번 스케쥴에 예약된 좌석을 조회하는 쿼리
SELECT 
    se_name '예약된 좌석번호'
FROM
    ticketing_list
    join (select * from ticketing where ti_num = 1) ti on ti_num = tl_ti_num
    join seat on se_num = tl_se_num;    
# 강남 CGV 1관의 좌석을 조회하는 쿼리
SELECT 
    se_name '좌석'
FROM
    seat
    join (select * from screen where sc_name = '1관') sc on sc_num = se_sc_num
    join (select * from theater where th_name = '강남 CGV') th on th_num = sc_th_num;

# 데드풀과 울버린 강남 CGV 1관 7/27 10:00에 예약 가능한 좌석을 조회하는 쿼리
select 
	mo_title '제목', th_name '극장', sc_name '상영관', sd_date '날짜', sd_time '시간', se_name '예약 가능한 좌석'
from
	seat
    join (select * from screen where sc_name = '1관') sc on sc_num = se_sc_num
    join (select * from theater where th_name = '강남 CGV') th on th_num = sc_th_num
    join (select * from schedule where sd_date = '2024-07-27' and sd_time = '10:00') sd on sd_sc_num = sc_num
    join (select * from movie where mo_title = '데드풀과 울버린') mo on mo_num = sd_mo_num
where se_name not in(SELECT 
						se_name
					FROM
						ticketing_list
						join ticketing on ti_num = tl_ti_num
						join seat on se_num = tl_se_num);
# 1번 스케줄에 예약 가능한 1번 상영관 좌석을 조회하는 쿼리
SELECT
    se_name '좌석'
FROM
    seat
    join (select * from screen where sc_name = '1관') sc on sc_num = se_sc_num
    join (select * from theater where th_name = '강남 CGV') th on th_num = sc_th_num
where se_name not in(SELECT 
						se_name
					FROM
						ticketing_list
						join (select * from ticketing where ti_num = 1) ti on ti_num = tl_ti_num
						join seat on se_num = tl_se_num);
# 1번 스케쥴에서 예약 가능한 좌석들의 수를 조회
SELECT
    count(se_name) '예약 가능한 좌석수'
FROM
    seat
    join (select * from screen where sc_name = '1관') sc on sc_num = se_sc_num
    join (select * from theater where th_name = '강남 CGV') th on th_num = sc_th_num
where se_name not in(SELECT 
						se_name
					FROM
						ticketing_list
						join (select * from ticketing where ti_num = 1) ti on ti_num = tl_ti_num
						join seat on se_num = tl_se_num);  

# 장르별 등록된 영화 개수를 조회하는 쿼리
SELECT 
    ge_name '장르', count(mg_num) '영화 개수'
FROM
    movie_genre
    right join genre on ge_name = mg_ge_name
group by ge_name;

# 현재 개봉한 영화를 조회하는 쿼리
select * from movie where mo_date <= now();

# 오늘부터 한 달 사이에 개봉한 영화를 조회하는 쿼리
select * from movie where mo_date between date_sub(now(), interval 1 month) and now();