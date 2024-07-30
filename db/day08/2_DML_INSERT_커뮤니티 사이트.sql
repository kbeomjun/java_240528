# 회원 상태를 추가 : 기간 정지, 영구 정지, 사용
insert into member_state values('기간 정지'), ('영구 정지'), ('사용');
# 신고 타입을 추가
insert into report_type values('욕설'), ('허위사실유포'), ('광고'), ('음란'), ('커뮤니티에 맞지 않음'), ('기타');
# '공지' 커뮤니티를 등록
insert into community(co_name) values('공지');

# 회원가입 id : abc123, pw : abc123, email : abc123@naver.com
# 회원가입 id : qwe123, pw : qwe123, email : qwe123@naver.com
# 회원가입 id : def123, pw : def123, email : def123@naver.com
# 관리자 id : admin123, pw : admin123, email : admin123@naver.com
insert into member(me_id, me_pw, me_email, me_authority, me_ms_name) values
('abc123', 'abc123', 'abc123@naver.com', 'USER', ('사용')),
('qwe123', 'qwe123', 'qwe123@naver.com', 'USER', ('사용')),
('def123', 'def123', 'def123@naver.com', 'USER', ('사용')),
('admin123', 'admin123', 'admin123@naver.com', 'ADMIN', ('사용'));

# 관리자가 '축구', '야구', '배구', '올림픽' 커뮤니티를 추가했을때 필요한 쿼리
insert into community(co_name) values('축구'), ('야구'), ('배구'), ('올림픽');
select * from community;
# abc123회원이 축구 커뮤니티에 게시글을 2개 작성(제목과 내용은 알아서)
insert into post(po_title, po_content, po_me_id, po_co_num) values('호날두', 'siuuuuuuuuuu', 'abc123', 2);
insert into post(po_title, po_content, po_me_id, po_co_num) values('메시', 'goat', 'abc123', 2);
# abc123회원이 야구 커뮤니티에 게시글을 1개 작성(제목과 내용은 알아서)
insert into post(po_title, po_content, po_me_id, po_co_num) values('야구1', '야구1', 'abc123', 3);
# abc123회원이 배구 커뮤니티에 게시글을 1개 작성(제목과 내용은 알아서)
insert into post(po_title, po_content, po_me_id, po_co_num) values('배구1', '배구1', 'abc123', 4);
# abc123회원이 올림픽 커뮤니티에 게시글을 3개 작성(제목과 내용은 알아서)
insert into post(po_title, po_content, po_me_id, po_co_num) values('올림픽1', '올림픽1', 'abc123', 5);
insert into post(po_title, po_content, po_me_id, po_co_num) values('올림픽2', '올림픽2', 'abc123', 5);
insert into post(po_title, po_content, po_me_id, po_co_num) values('올림픽3', '올림픽3', 'abc123', 5);
# qwe123회원이 축구 커뮤니티에 게시글 2개 작성(제목과 내용은 알아서)
insert into post(po_title, po_content, po_me_id, po_co_num) values('축구1', '축구1', 'qwe123', 2);
# qwe123회원이 올림픽 커뮤니티에 게시글 1개 작성(제목과 내용은 알아서)
insert into post(po_title, po_content, po_me_id, po_co_num) values('올림픽4', '올림픽4', 'qwe123', 5);

# qwe123회원이 1번 게시글을 클릭해서 상세를 확인했을때 쿼리
update post set po_views = po_views + 1 where po_num = 1;
# 1번 게시글에 qwe123회원이 추천을 눌렀을때 쿼리
insert into recommend(re_state, re_po_num, re_me_id) values(1, 1, 'qwe123');
# 1번 게시글에 qwe123회원이 추천을 눌렀을때 쿼리
update recommend set re_state = 0 where re_po_num = 1 and re_me_id = 'qwe123';

# qwe123회원이 1,2,3 게시글은 추천, 4,5,6번 게시글은 비추천
insert into recommend(re_state, re_po_num, re_me_id) values
(1, 1, 'qwe123'), (1, 2, 'qwe123'), (1, 3, 'qwe123'),
(-1, 4, 'qwe123'), (-1, 5, 'qwe123'), (-1, 6, 'qwe123');
# def123회원이 3,4,5 게시글은 추천, 7,8번 게시글은 비추천
insert into recommend(re_state, re_po_num, re_me_id) values
(1, 3, 'def123'), (1, 4, 'def123'), (1, 5, 'def123'),
(-1, 7, 'def123'), (-1, 8, 'def123');
insert into recommend(re_state, re_po_num, re_me_id) values
(1, 1, 'abc123'), (1, 2, 'abc123'), (1, 3, 'abc123'), (1, 4, 'abc123'),
(1, 5, 'abc123'), (1, 6, 'abc123'), (1, 7, 'abc123'), (1, 8, 'abc123');

# 1번 게시글에 각 회원이 다음 순서로 댓글을 작성. -는 대댓
# abc123 : 작성자입니다.
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_PO_NUM, CM_ORI_NUM, CM_ME_ID)
SELECT IFNULL(MAX(CM_NUM), 0) + 1, '작성자입니다.', 1, IFNULL(MAX(CM_NUM), 0) + 1 , 'abc123' FROM COMMENT;
# - qwe123 : 반가워요 
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_PO_NUM, CM_ORI_NUM, CM_ME_ID)
SELECT IFNULL(MAX(CM_NUM), 0) + 1, '반가워요', 1, 1 , 'qwe123' FROM COMMENT;
# - def123 : 저도 반가워요
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_PO_NUM, CM_ORI_NUM, CM_ME_ID)
SELECT IFNULL(MAX(CM_NUM), 0) + 1, '저도 반가워요', 1, 1 , 'def123' FROM COMMENT;
# qwe123 : 어떻게 활성화 시킬가요?
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_PO_NUM, CM_ORI_NUM, CM_ME_ID)
SELECT IFNULL(MAX(CM_NUM), 0) + 1, '어떻게 활성화 시킬가요?', 1, IFNULL(MAX(CM_NUM), 0) + 1 , 'qwe123' FROM COMMENT;
# def123 : 모르겠어요
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_PO_NUM, CM_ORI_NUM, CM_ME_ID)
SELECT IFNULL(MAX(CM_NUM), 0) + 1, '모르겠어요', 1, IFNULL(MAX(CM_NUM), 0) + 1 , 'def123' FROM COMMENT;
# abc123 : 노력해봐요. 
INSERT INTO COMMENT(CM_NUM, CM_CONTENT, CM_PO_NUM, CM_ORI_NUM, CM_ME_ID)
SELECT IFNULL(MAX(CM_NUM), 0) + 1, '노력해봐요.', 1, IFNULL(MAX(CM_NUM), 0) + 1 , 'abc123' FROM COMMENT;

# 1번 게시글에서 6번 댓글을 기타로 qwe123회원이 신고함 
INSERT INTO REPORT(RP_ME_ID, RP_TABLE, RP_TARGET, RP_RT_NAME) 
VALUES('qwe123', 'comment', 6, '기타');
# 2번 게시글을 def123회원이 기타로 신고함 
INSERT INTO REPORT(RP_ME_ID, RP_TABLE, RP_TARGET, RP_RT_NAME) 
VALUES('qwe123', 'post', 2, '기타');