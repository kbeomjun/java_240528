# 데이터베이스 생성
# if not exists를 추가하는 이유
# -> 생성하려는 DB가 이미 있는 경우를 건너뛰기 위해서
# -> if not exists가 없는 경우 생성하려는 DB가 있으면 에러가 발생해서 이후 SQL문이 실행되지 않음
# MySQL에서는 schema와 DB를 같은것으로 봄
# create database if not exists DB명
# create schema if not exists DB명
# 워크벤치에서 Schemas 아래 공백에 우클릭 후 Create Schema 클릭 후 DB명 입력하고 apply
create database if not exists world;
create database if not exists sakila;
create database if not exists student;

# 데이터베이스 삭제
# drop database if exists DB명;
# drop schema if exists DB명;
# 워크스페이스에서 DB명 우클릭 후 Drop Schemas... 클릭
drop database if exists student;

# 데이터베이스 수정 - 이름은 수정이 안됨, 문자 집합을 수정
# ALTER SCHEMA DB명 기본문자집합 바꿀문자집합 기본COLLATE 바꿀COLLATE;
# 워크스페이스에서 DB명에 호버하고 두번째 아이콘 클릭 후 원하는 문자집합과 collate를 선택 후 apply