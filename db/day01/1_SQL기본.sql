/* 여러줄 주석 */
-- 한줄 주석
# 한줄 주석
# ctrl+enter : 커서가 있는 줄만 실행, ctrl+shift+enter : 전체 실행

# 데이터베이스 목록을 조희
# 워크벤치에서 Schemas에 해당
show databases;

# 데이터베이스를 선택
# 워크 벤치에서 데이터베이스를 더블 클릭
use world;

# 선택한 데이터베이스에 있는 테이블들을 조회
# 데이터베이스 밑에 Tables를 더블 클릭
show tables;

# 테이블 정보 확인
# 워크벤치에서는 테이블 클릭 또는 테이블에 마우스 호버 후 2번째 아이콘 클릭
desc city;
explain city;