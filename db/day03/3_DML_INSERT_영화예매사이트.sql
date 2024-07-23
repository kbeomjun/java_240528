# 사용자가 아이디 abc123, 비번 abc123으로 회원가입을 진행했을 때 사용하는 쿼리
# 권한은 관리자인 경우 'ADMIN', 사용자인 경우 'USER'
insert into member value('abc123', 'abc123', 'USER');

# 관리자가 아이디 admin1, 비번 admin1로 회원가입을 진행했을 때 사용하는 쿼리
insert into member value('admin1', 'admin1', 'ADMIN');

# 영화 정보를 추가하는 작업(데드풀과 울버린)
# 1. 영화 정보를 추가 : 제목, 내용, 시간, 연령대, 개봉일
insert into movie(mo_title, mo_content, mo_time, mo_age, mo_date) 
values('데드풀과 울버린', 
'히어로 생활에서 은퇴한 후,
평범한 중고차 딜러로 살아가던 ‘데드풀’이
예상치 못한 거대한 위기를 맞아
모든 면에서 상극인 ‘울버린’을 찾아가게 되며 펼쳐지는
도파민 폭발 액션 블록버스터',
127, '청소년관람불가', '2024-07-24');
# 2. 국가를 등록 : 한국, 미국, 영국, 일본, 중국
insert into country values('한국'), ('미국'), ('영국'), ('일본'), ('중국');
# 3. 영화 참여 국가를 추가 : 데드풀과 울버린 영화의 참여 국가
insert into movie_country(mc_co_name, mc_mo_num) values('미국', 1);
# 4. 장르를 추가 : 액션, 코미디, 멜로, 공포, 스릴러
insert into genre values('액션'), ('코미디'), ('멜로'), ('공포'), ('스릴러');
# 5. 영화 장르를 추가 : 데드풀과 울버린 영화의 장르
insert into movie_genre(mg_ge_name, mg_mo_num) values('액션', 1), ('코미디', 1); 
# 6. 영화인을 등록 : 데드풀과 울버린 영화에 나오는 모든 영화인
insert into person(ps_name, ps_birth, ps_detail, ps_country) values
('숀 레비', '1968-07-28', '', '캐나다'),
('라이언 레이놀즈', '1976-10-23', '<엑스맨 탄생: 울버린> 등의 액션 블록버스터와 호러, 로맨틱 무비...', '캐나다'),
('휴 잭맨', '1968-10-12', '호주 출신 배우인 휴 잭맨은...', '오스트레일리아'),
('엠마 코린', '1995-12-13', '', ''),
('모레나 바카린', '1979-06-02', '', '브라질'),
('롭 딜레이니', '1977-01-19', '', '미국'),
('레슬리 우감스', '1943-05-25', '', ''),
('카란 소니', '1985-01-08', '', '인도'),
('매튜 맥퍼딘', '1974-10-17', '', '영국');
# 7. 캐릭터를 등록 : 데드풀과 울버린 영화에 나오는 영화인의 역할, 사진은 NULL
insert into `character`(ch_role, ch_pic, ch_ps_num) values
('감독', NULL, 1),
('배우', NULL, 2),
('배우', NULL, 3),
('배우', NULL, 4),
('배우', NULL, 5),
('배우', NULL, 6),
('배우', NULL, 7),
('배우', NULL, 8),
('배우', NULL, 9);
# 8. 캐스팅을 등록 : 데드풀과 울버린 영화에 나오는 캐릭터들의 캐스팅 이름
insert into casting(ca_name, ca_ch_num, ca_mo_num) values
('감독', 1, 1),
('데드풀', 2, 1),
('로건', 3, 1),
('카산드라 노바', 4, 1),
('바네사 칼라일', 5, 1),
('피터', 6, 1),
('블라인드 앨', 7, 1),
('도핀더', 8, 1),
('패러독스', 9, 1);

# 강남 CGV를 등록, 상영관 : 3개, 좌석 : 30개
# 1관 : A1~A3, B1~B3, C1~C4
# 2관 : A1~A4, B1~B4, C1~C2
# 3관 : A1~A2, B1~B2, C1~C2, D1~D2, E1~E2
insert into theater values(null, '강남 CGV', 3, 30, '서울특별시 강남구 강남대로 438 스타플렉스 4층~8층', '강남');
insert into screen values
(null, '1관', 10, 1),
(null, '2관', 10, 1),
(null, '3관', 10, 1);
insert into seat values
(null, 'A1', 1), (null, 'A2', 1), (null, 'A3', 1), (null, 'B1', 1), (null, 'B2', 1), (null, 'B3', 1), (null, 'C1', 1), (null, 'C2', 1), (null, 'C3', 1), (null, 'C4', 1),
(null, 'A1', 2), (null, 'A2', 2), (null, 'A3', 2), (null, 'A4', 2), (null, 'B1', 2), (null, 'B2', 2), (null, 'B3', 2), (null, 'B4', 2), (null, 'C1', 2), (null, 'C2', 2),
(null, 'A1', 3), (null, 'A2', 3), (null, 'B1', 3), (null, 'B2', 3), (null, 'C1', 3), (null, 'C2', 3), (null, 'D1', 3), (null, 'D2', 3), (null, 'E1', 3), (null, 'E2', 3);

# 영등포 CGV를 등록, 상영관 : 4개, 좌석 : 44개
# 1관 : A1~A3, B1~B3, C1~C4
# 2관 : A1~A4, B1~B4, C1~C2
# 3관 : A1~A3, B1~B3, C1~C3, D1~D3
# 4관 : A1~A6, B1~B6
insert into theater values(null, '영등포 CGV', 4, 44, '서울특별시 영등포구 영중로 15 타임스퀘어 4F', '영등포');
insert into screen values
(null, '1관', 10, 2),
(null, '2관', 10, 2),
(null, '3관', 12, 2),
(null, '4관', 12, 2);
insert into seat values
(null, 'A1', 4), (null, 'A2', 4), (null, 'A3', 4), (null, 'B1', 4), (null, 'B2', 4), (null, 'B3', 4), (null, 'C1', 4), (null, 'C2', 4), (null, 'C3', 4), (null, 'C4', 4),
(null, 'A1', 5), (null, 'A2', 5), (null, 'A3', 5), (null, 'A4', 5), (null, 'B1', 5), (null, 'B2', 5), (null, 'B3', 5), (null, 'B4', 5), (null, 'C1', 5), (null, 'C2', 5),
(null, 'A1', 6), (null, 'A2', 6), (null, 'A3', 6), (null, 'B1', 6), (null, 'B2', 6), (null, 'B3', 6), (null, 'C1', 6), (null, 'C2', 6), (null, 'C3', 6), (null, 'D1', 6), (null, 'D2', 6), (null, 'D3', 6),
(null, 'A1', 7), (null, 'A2', 7), (null, 'A3', 7), (null, 'A4', 7), (null, 'A5', 7), (null, 'A6', 7), (null, 'B1', 7), (null, 'B2', 7), (null, 'B3', 7), (null, 'B4', 7), (null, 'B5', 7), (null, 'B6', 7);