# 제품 분류를 등록하는 쿼리
insert into category(ca_name) values('옷'), ('모자'), ('신발'), ('악세서리'), ('기타');

# 사용자 회원가입하는 쿼리
# id : abc123, pw : abc123, 번호 : 011-1234-5678, email : abc123@naver.com
# id : qwe123, pw : qwe123, 번호 : 011-1111-2222, email : qwe123@naver.com
insert into member(me_id, me_pw, me_email, me_phone, me_authority, me_fail) values
('abc123', 'abc123', 'abc123@naver.com', '011-1234-5678', 'USER', 0),
('qwe123', 'qwe123', 'qwe123@naver.com', '011-1111-2222', 'USER', 0);

# 제품 등록하는 쿼리
# 분류 : 옷, 코드 : CLO001, 상품명 : 시원한 티셔츠, 가격 : 20000, 내용 : 티셔츠를 입으면 시원해요.
# 분류 : 옷, 코드 : CLO002, 상품명 : 시원한 반바지, 가격 : 15000, 내용 : 여름 전용 반바지입니다.
# 분류 : 옷, 코드 : CLO003, 상품명 : 시원한 양말, 가격 : 20000, 내용 : 시원한 의류 시리즈 마지막 용품입니다.
insert into product values
('CLO001', '시원한 티셔츠', '티셔츠를 입으면 시원해요.', 20000, (select ca_num from category where ca_name = '옷')),
('CLO002', '시원한 반바지', '여름 전용 반바지입니다.', 15000, (select ca_num from category where ca_name = '옷')),
('CLO003', '시원한 양말', '시원한 의류 시리즈 마지막 용품입니다.', 20000, (select ca_num from category where ca_name = '옷'));
# 분류 : 모자, 코드 : CAP001, 상품명 : 여름 모자, 가격 : 30000, 내용 : 그늘을 만들어줘요.
insert into product values
('CAP001', '여름 모자', '그늘을 만들어줘요.', 30000, (select ca_num from category where ca_name = '모자'));
# 분류 : 신발, 코드 : SHO001, 상품명 : 싼 크룩스, 가격 : 19999, 내용 : 인기 신발.
insert into product values
('SHO001', '싼 크룩스', '그늘을 만들어줘요.', 19999, (select ca_num from category where ca_name = '신발'));
# 분류 : 악세서리, 코드 : ACC001, 상품명 : 금 목걸이, 가격 : 100000, 내용 : 부의 상징.
insert into product values
('ACC001', '금 목걸이', '그늘을 만들어줘요.', 100000, (select ca_num from category where ca_name = '악세서리'));