# 문자열 함수
# 길이관련 함수
# length(문자열) : 문자열의 바이트수를 반환
select length("안녕"); # 6바이트
select length("12"); # 2바이트
# char_length(문자열) : 문자열의 길이를 반환
select char_length("안녕"); # 2
select char_length("12"); # 2

# concat(문자열1, 문자열2, ...) : 문자열들을 이어서 하나의 문자열을 만듬
select concat('안녕', '하', '세요', '.');

# 탐색
# field(찾을문자열, 문자열1, 문자열2, ...) : 문자열들에서 찾을 문자열이 몇번째에 있는지 반환
# 1부터 시작, 없으면 0
select field('안녕', '안녕하세요.', '안녕1');
# instr(문자열1, 문자열2) : 문자열1에서 문자열2가 몇번째부터 시작하는지 반환, 없으면 0
select instr("안녕하세요.", "안녕");
select instr("안녕하세요.", "안녕1");
# locate(문자열2, 문자열1) : 문자열1에서 문자열2가 몇번째부터 시작하는지 반환, 없으면 0
select locate("안녕", "안녕하세요.");
select locate("안녕1", "안녕하세요.");

# format(숫자, 소수점자리수) : 숫자를 소수점 자리수까지 표현, 3자리마다 ,를 추가
select format(1234567.89012, 2);

# bin(숫자) : 2진수, oct(숫자) : 8진수, hex(숫자) : 16진수
select bin(31), oct(31), hex(31);

# lpad(문자열, 자리, 채울문자열) : 문자열이 자리수보다 작으면 왼쪽에 채울문자열을 추가하여 자리수를 맞춤
# rpad(문자열, 자리, 채울문자열) : 문자열이 자리수보다 작으면 오른쪽에 채울문자열을 추가하여 자리수를 맞춤
select lpad('1', 4, '1234'), rpad('1', 4, '1234');
# rgb(255,20,3)을 16진수로 변경해서 #XXXXXX으로 표현하는 쿼리
select concat('#', lpad(hex(255), 2, '0'), lpad(hex(20), 2, '0'), lpad(hex(3), 2, '0')) as 'rgb(255,20,3)';

# 문자열 교체
# insert(문자열, 위치, 길이, 대체문자열) : 문자열에서 위치부터 길이만큼 문자열을 제거 후 대체문자열을 추가
select insert('누구나 하는 C', 8, 1, 'JAVA');
# replace(문자열, 교체할문자열, 대체할문자열)
select replace('C의 정석', 'C', 'JAVA');
# upper(문자열) : 대문자로, lower(문자열) : 소문자로
select upper('hi'), lower('HI');
# reverse(문자열) : 문자열을 역순으로
select reverse('ABCDEF');
# repeat(문자열, 횟수) : 문자열을 횟수만큼 반복
select repeat('HI!', 3);

# 부분 문자열 추출
# left(문자열, 숫자) : 문자열에서 왼쪽부터 숫자만큼 문자열을 반환
# right(문자열, 숫자) : 문자열에서 오른쪽부터 숫자만큼 문자열을 반환
select left('TEST.JPG', 4), right('TEST.JPG', 3);
# substring(문자열, 시작위치, 길이) : 문자열에서 시작위치부터 길이만큼의 문자열을 반환
select substring('TEST.JPG', 1, 4), substring('TEST.JPG', 6, 3);

# 시간함수
# now(), sysdate(), current_timestamp : 현재 시간을 반환
# current_timestamp는 속성의 타입이 datetime인 경우 기본값으로 현재시간을 추가하도록 설정할 수 있음
select now(), sysdate(), current_timestamp();

# adddate(시간, 차이)/subdate(시간, 차이) : 시간에서 차이만큼 일을 더한/뺀 시간을 계산
select adddate(now(), 2), subdate(now(), 2);

# addtime(시간, 차이)/subtime(시간, 차이) : 시간에서 차이만큼 시간(시,분,초)을 더한/뺀 시간을 계산
select addtime(now(), '2:00:00'), subtime(now(), '2:00:00');

# year(시간) : 시간에서 년을 추출
# month(시간) : 시간에서 월을 추출
# day(시간) : 시간에서 일을 추출
select year(now()), month(now()), day(now());

# hour(시간), minute(시간), second(시간), microsecond(시간) : 시간에서 시/분/초/밀리초를 추출
select hour(now()), minute(now()), second(now());
select microsecond('2024-07-27 14:10:10.123');

# date(시간) : 년-월-일 추출
# time(시간) : 시:분:초 추출
select date(now()), time(now());

# datediff(시간1, 시간2) : 시간1에서 시간2의 차이를 일로 반환, 시간1 - 시간2
# timediff(시간1, 시간2) : 시간1에서 시간2의 차이를 시:분:초로 반환, 시간1 - 시간2
select datediff(now(), '2024-07-27 10:00:00');
select timediff('11:00', '09:00');
select timediff(now(), '2024-07-27 10:00:00');

# date_add(시간, interval) : 시간에서 interval만큼 더한 시간
# date_sub(시간, interval) : 시간에서 interval만큼 뺀 시간
/*
interval 종류
- 단일 시간 유형 : 한 종류의 단위를 나타냄
    - year, month, day, hour, minute, second, microsecond, quater(분기), week(주)
- 복합 시간 유형 : 여러 종류의 단위를 한 번에 나타냄
	- year_month : 년 월
    - day_hour : 일 시간
    - day_minute : 일 시간:분
    - day_microsecond : 일 시간:분:초.마이크로초
*/
select date_add(now(), interval 1 month);
select date_sub(now(), interval '1 1' year_month);

# 수학 함수
# floor(숫자) : 소수점 첫번째 자리 내림
# ceil(숫자) : 소수점 첫번째 자리 올림 
# round(숫자) : 소수점 첫번째 자리 반올림
# abs(숫자) : 절댓값
select floor(1.23), ceil(1.23), round(1.23), abs(-1);